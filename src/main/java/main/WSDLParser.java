package main;

import com.predic8.wsdl.Message;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Part;
import com.predic8.wsdl.PortType;
import groovy.xml.QName;
import se.kth.ict.id2208.matching.MatchedElementType;
import se.kth.ict.id2208.matching.MatchedOperationType;
import se.kth.ict.id2208.matching.MatchedWebServiceType;
import se.kth.ict.id2208.matching.WSMatchingType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by antondahlin on 2016-03-01.
 */
public class WSDLParser {


    private String namespace = "http://www.w3.org/2001/XMLSchema";
    private Wordnet wordnet;
    private final double LOWERBOUND = 0.52;
    private List<WSDLFile> wsdlFiles1 = new ArrayList<WSDLFile>();
    private List<WSDLFile> wsdlFiles2 = new ArrayList<WSDLFile>();
    private String wsdlPath = "./src/main/resources/WSDLs/";

    public WSDLParser() {
        wordnet = new Wordnet();
        loadWSDLs();
    }


    public void loadWSDLs(){

        File wsdls = new File(wsdlPath);

        for(File file: wsdls.listFiles()){
            WSDLFile wsdlFile = new WSDLFile(wsdlPath+file.getName());
            wsdlFiles1.add(wsdlFile);
            wsdlFiles2.add(wsdlFile);
        }
    }

    public void match() {
        WSMatchingType wsMatchingType = new WSMatchingType();

        for(WSDLFile wsdlFile1: wsdlFiles1) {
            for(WSDLFile wsdlFile2: wsdlFiles2) {
                if(wsdlFile1.equals(wsdlFile2)) continue;

                MatchedWebServiceType matchedWebServiceType = match(wsdlFile1, wsdlFile2);
                if(matchedWebServiceType != null)
                    wsMatchingType.getMacthing().add(matchedWebServiceType);
            }
        }
    }

    private MatchedWebServiceType match(WSDLFile wsdlFile1, WSDLFile wsdlFile2) {
        MatchedWebServiceType matchedWebServiceType = new MatchedWebServiceType();
        matchedWebServiceType.setInputServiceName(wsdlFile1.getService().getName());
        matchedWebServiceType.setOutputServiceName(wsdlFile2.getService().getName());  

        double serviceScore = 0.0; int operationsCount = 0;

        // Get all port types from wsdlFile1
        for(PortType inPortType: wsdlFile1.getDefinitions().getPortTypes()) {
            // For each operation in portType of wsdlFile1
            for(Operation inOp: inPortType.getOperations()) {
                MatchedOperationType operation = new MatchedOperationType();

                double opScore = 0.0; int elementCount = 0;
                operation.setInputOperationName(inOp.getName());
                Message inMessage = inOp.getInput().getMessage();

                // For each part of the message wsdlFile1
                for(Part inPart: inMessage.getParts()) {
                    if(!isSimple(inPart)) continue;
                    String inputPart = inPart.getName(); 

                    // Compare it with all output operations of wsdlFile2
                    for(PortType outPortType: wsdlFile2.getDefinitions().getPortTypes()) {
                        
                        for (Operation outOp: outPortType.getOperations()) {
                            operation.setOutputOperationName(outOp.getName());
                            Message outMessage = outOp.getOutput().getMessage();

                            for(Part outPart: outMessage.getParts()) {
                                if(!isSimple(outPart)) continue;

                                String outputPart = outPart.getName();

                                double score = wordnet.calculateScore(inputPart, outputPart);

                                if(score >= LOWERBOUND) {
                                    opScore += score;
                                    elementCount++;

                                    MatchedElementType matchedElementType = new MatchedElementType();
                                    matchedElementType.setInputElement(inputPart);
                                    matchedElementType.setOutputElement(outputPart);
                                    matchedElementType.setScore(score);

                                    operation.getMacthedElement().add(matchedElementType);
                                }
                            }
                        }
                    }
                }
                if(!operation.getMacthedElement().isEmpty()) {
                    // If we actually had matches
                    double opFinalScore = opScore / elementCount;
                    operation.setOpScore(opFinalScore);
                    serviceScore += opFinalScore;
                    operationsCount++;
                    matchedWebServiceType.getMacthedOperation().add(operation);
                }
            }
        }
        if(!matchedWebServiceType.getMacthedOperation().isEmpty()) {
            double serviceFinalScore = serviceScore / operationsCount;
            matchedWebServiceType.setWsScore(serviceFinalScore);

            // Print some shit
            System.out.println("MATCHES FOUND FOR SERVICE : " + matchedWebServiceType.getInputServiceName());
            System.out.println("MATCHED WITH : " + matchedWebServiceType.getOutputServiceName());
            for(MatchedOperationType op: matchedWebServiceType.getMacthedOperation()) {
                System.out.println("--Operation: " + op.getInputOperationName());
                System.out.println("++Operation: " + op.getOutputOperationName());
                for(MatchedElementType el: op.getMacthedElement())
                    System.out.println("==== " + el.getInputElement() + " <-> " + el.getOutputElement() + "(" + el.getScore() + ")");
            }
        }
        else
            return null;

        return matchedWebServiceType;
    }

    private boolean isSimple(Part part) {
        QName qname = part.getType() == null ? part.getElement().getType() :
                part.getType().getQname();
        if (qname == null) return false;
        String ns = qname.getNamespaceURI();
        String l = qname.getLocalPart();
        return ns.equals(namespace) &&
                (l.equals("int") || l.equals("double") || l.equals("date") || l.equals("string")||
                        l.equals("double"));
    }
}
