/*
 * This class represents a parser that converts an XML file to a database
 */
package model.db;

import controller.Controller;
import controller.Settings;
import model.db.errors.ObjectCreationException;
import model.street.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.PrintWriter;

public class DatabaseXmlParser {
    Database database;

    /**
     * Parse an XML file and ADD it's information to the database (non destructive)
     *
     * @param filePath the file path of the XML file
     */
    public static void parse(String filePath) {
        try {
            PrintWriter parsingLogger = new PrintWriter(System.out);
            Controller controller = Controller.getInstance();
            Database db = controller.getDatabaseInstance();
            MyPrototypeFactory objectsFactory = Controller.getInstance().getFactory();
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            parsingLogger.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList buildingsList = doc.getElementsByTagName("Building");

            // Goes over all buildings in this 'Data' object (tag)
            for (int buildingIndex = 0; buildingIndex < buildingsList.getLength(); buildingIndex++) {

                Node buildingNode = buildingsList.item(buildingIndex);

                parsingLogger.print("Currently parsed Element :" + buildingNode.getNodeName());

                if (buildingNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element buildingElement = (Element) buildingNode;
                    parsingLogger.print("Street Name : " + buildingElement.getElementsByTagName("street").item(0).getTextContent());
                    parsingLogger.println("Street Number : " + buildingElement.getElementsByTagName("buildingNumber").item(0).getTextContent());
                    //Create the building
                    Building building = null;
                    try {
                        building = (Building) objectsFactory.createObject("Building");
                        building.setStreetName(buildingElement.getElementsByTagName("street").item(0).getTextContent());
                        building.setStreetNumber(Integer.parseInt(buildingElement.getElementsByTagName("buildingNumber").item(0).getTextContent()));
                        if (!db.isBuildingExist(building.getAddress())) {
                            db.addBuilding(building.getAddress(), building);
                        }
                    } catch (ObjectCreationException e) {
                        parsingLogger.println(e.getMessage());
                    }
                    NodeList apartmentsList = buildingElement.getElementsByTagName("Apartments");

                    Node apartmentListNode = apartmentsList.item(0);
                    parsingLogger.println("Current Element :" + apartmentListNode.getNodeName());

                    if (apartmentListNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element apartmentsListElement = (Element) apartmentListNode;
                        NodeList apartmentAttributes = apartmentsListElement.getChildNodes();

                        // Goes over all the apartments in this building
                        for (int apartmentIndex = 0; apartmentIndex < apartmentAttributes.getLength(); apartmentIndex++) {
                            Apartment apartment = null;
                            RegularApartment regularApartment = null;
                            GardenApartment gardenApartment = null;
                            Penthouse penthouseApartment = null;

                            if (apartmentAttributes.item(apartmentIndex).getNodeType() == Node.ELEMENT_NODE) {
                                Element apartmentType = (Element) apartmentAttributes.item(apartmentIndex);
                                parsingLogger.println("Apartment type is: " + apartmentType.getTagName());

                                //Create Apartment
                                String apartmentTypeName = apartmentType.getTagName();
                                try {
                                    if (apartmentTypeName.contentEquals("RegularApartment")) {
                                        apartment = (RegularApartment) controller.getFactory().createObject("Regular");
                                    } else if (apartmentTypeName.contentEquals("GardenApartment")) {
                                        apartment = (GardenApartment) controller.getFactory().createObject("Garden");
                                    } else if (apartmentTypeName.contentEquals("Penthouse")) {
                                        apartment = (Penthouse) controller.getFactory().createObject("Penthouse");
                                    }
                                } catch (ObjectCreationException e) {
                                    parsingLogger.println(e.getMessage());
                                    continue; //Failed creating this apartment. move on to the next (fallback)
                                }
                                //Apartment type is unknown in run-time
                                try {
                                    regularApartment = (RegularApartment) apartment;
                                } catch (Exception e) {
                                    //Unable to convert to 'regular apartment' trying a garden apartment
                                    try {
                                        gardenApartment = (GardenApartment) apartment;
                                    } catch (Exception e1) {
                                        //Unable to convert to 'Garden apartment' trying a penthouse
                                        try {
                                            penthouseApartment = (Penthouse) apartment;
                                        } catch (Exception e2) {
                                            //Oh dear, you shouldn't be here
                                        }
                                    }

                                }
                                NodeList apartmentsAttributesList = apartmentType.getChildNodes();

                                //Goes over each apartment's attributes
                                for (int attributeIndex = 0; attributeIndex < apartmentsAttributesList.getLength(); attributeIndex++) {
                                    if (apartmentsAttributesList.item(attributeIndex).getNodeType() == Node.ELEMENT_NODE) {
                                        Element apartmentAttribute = (Element) apartmentsAttributesList.item(attributeIndex);
                                        parsingLogger.println("Apartment attribute name is: " + apartmentAttribute.getNodeName());
                                        parsingLogger.println("Apartment attribute value is: " + apartmentAttribute.getTextContent());
                                        //Add attributes to the apartment, based on the attribute name
                                        try {
                                            apartment.setAddress(building.getAddress());
                                            String attributeName = apartmentAttribute.getNodeName();
                                            String attributeValue = apartmentAttribute.getTextContent();
                                            if (attributeName.contentEquals("apartmentNumber")) {
                                                apartment.setApartmentNumber(Integer.parseInt(attributeValue));
                                            } else if (attributeName.contentEquals("floor")) {
                                                apartment.setFloor(Integer.parseInt(attributeValue));
                                            } else if (attributeName.contentEquals("sizeOfApartment")) {
                                                apartment.setArea(Double.parseDouble(attributeValue));
                                            } else if (attributeName.contentEquals("numberOfRooms")) {
                                                apartment.setNumOfRooms(Integer.parseInt(attributeValue));
                                            } else if (attributeName.contentEquals("ownerName")) {
                                                apartment.setResidentName(attributeValue);
                                            } else if (attributeName.contentEquals("storageArea")) {
                                                regularApartment.setWarehouseArea(Float.parseFloat(attributeValue));
                                            } else if (attributeName.contentEquals("balconyArea")) {
                                                penthouseApartment.setBalconyArea(Double.parseDouble(attributeValue));
                                            } else if (attributeName.contentEquals("gardenArea")) {
                                                gardenApartment.setGardenArea(Integer.parseInt(attributeValue));
                                            } else if (attributeName.contentEquals("separateEntrance") && apartment instanceof GardenApartment) {
                                                if (attributeValue.equalsIgnoreCase("true")) {
                                                    gardenApartment.setSeparateEntrance(true);
                                                } else {
                                                    gardenApartment.setSeparateEntrance(false);
                                                }
                                            }
                                        } catch (Exception e) {
                                            parsingLogger.println("failed creating: " + apartmentTypeName);
                                        }
                                    }
                                }
                            }
                            if (apartment != null) {
                                db.addApartment(apartment);
                            }
                        }
                    }
                }
                //Debugging
                if (Settings.DEBUG_MODE.equalsIgnoreCase("full")) {
                    parsingLogger.flush();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
