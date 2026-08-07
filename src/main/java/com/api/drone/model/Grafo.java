package com.api.drone.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Grafo {

    private final Map<Node, List<Node>> adjacencias = new HashMap<>();

    public void addNode(Node node) {
        adjacencias.put(node,new ArrayList<>());
    }

    public void addEdge(Node origem, Node destino){
        adjacencias.get(origem).add(destino);
        adjacencias.get(destino).add(origem);
    }
    public void removerEdge(Node origem, Node destino){
        if(adjacencias.containsKey(origem)){
            adjacencias.get(origem).remove(destino);
        }

        if(adjacencias.containsKey(destino)){
            adjacencias.get(destino).remove(origem);
        }
    }

    public boolean checkEdge(Node origem, Node destino) {

     return adjacencias.get(origem).contains(destino);
    }

    public List<Node> getAdjacentes(Node node){
        return adjacencias.get(node);
    }

    public Node buscarNode(int x, int y){
        for(Node node: adjacencias.keySet()){
            if(node.getX() == x && node.getY() == y ){
                return node;
            }
        }
        return null;
    }

}
