package com.baggage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.baggage.service.Algo;
import com.baggage.service.AlgoImpl;
import com.baggage.vo.Bag;
import com.baggage.vo.Edge;
/*
 * Run MainApp class
 */
public class AirportBaggageMainApp {

	private final static String SECTION="# Section:";
	private final static String ARRIVAL ="ARRIVAL";
    private final static String BAGGAGE_CLAIM ="BaggageClaim";
	
	public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		System.out.println("Please enter input and enter done: \n");
		List<Edge> edges = parseInputGraph(sc);
		Algo algo = new AlgoImpl();
		
		Map<String,String> departuresMap=parseInputDepartures(sc); 
		List<Bag> bagList = parseInputBgs(sc);
		sc.close();
		for(Bag bag : bagList){
			String bagId = bag.getId();
			String bagentry = bag.getEntryPointOfBag();
			String flight = bag.getFlightToBeSent();
			
			String destination;
			if(flight.equals(ARRIVAL)){
				destination = BAGGAGE_CLAIM; 
			}else{
				destination = departuresMap.get(flight);
			}
			String path = algo.shortestPath(bagentry, destination, edges);
			System.out.println(bagId+" "+path);
			
		}
	}

	private static List<Bag> parseInputBgs(Scanner sc) {
		String strings ;
        List<Bag> bags= new ArrayList<>();
        do{
            strings = sc.nextLine();
            String [] parts = strings.trim().split("\\s+");
            if(parts.length >=3){
                Bag bag= new Bag(parts[0],parts[1],parts[2]);
                bags.add(bag);
            }else{
                sc.close();
                break;
            }
        }while(sc.hasNextLine());
        return bags;
    }	

	private static Map<String, String> parseInputDepartures(Scanner sc) {
		String next  = sc.nextLine();
		Map<String,String> departure = new HashMap<>();
		while(!next.startsWith(SECTION)){
			String [] strings = next.trim().split("\\s+");
			if(strings.length >= 2){
				departure.put(strings[0],strings[1]);
            }else{
                throw new IllegalArgumentException("Illegal arguments or inputs.");
            }
            next=sc.nextLine();
		}
		return departure;
	}

	private static List<Edge> parseInputGraph(Scanner sc) {
		
		String next = sc.nextLine();
		List<Edge> edges = new ArrayList<>();
		next = sc.nextLine();
		while(!next.startsWith(SECTION)){
			String[] strings = next.trim().split("\\s+");
			if(strings.length >= 3){
				Edge edge = new Edge(strings[0], strings[1], Integer.valueOf(strings[2]));
				edges.add(edge);
				
				Edge reverseEdge = new Edge(strings[1],strings[0],Integer.valueOf(strings[2]));
                edges.add(reverseEdge);
			}else{
				throw new IllegalArgumentException("Wrong input data is given");
			}
			next = sc.nextLine();
		}
		return edges;
	}
}
