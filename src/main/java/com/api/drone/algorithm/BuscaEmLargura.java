package com.api.drone.algorithm;

import com.api.drone.model.Grafo;
import com.api.drone.model.Node;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class BuscaEmLargura {


    private final Grafo grafo;

    public BuscaEmLargura(Grafo grafo) {
        this.grafo = grafo;
    }

    public List<Node> buscaEmLargura(Node inicio, Node fim) {
        Queue<Node> fila = new LinkedList<>();
        Set<Node> visitado =new HashSet<>();
        Map<Node, Node> anterior = new HashMap<>();

        fila.add(inicio);
        visitado.add(inicio);
        anterior.put(inicio, null);
        Node atual;
        while (!fila.isEmpty()) {
          atual = fila.poll();
            if (atual.equals(fim)) break;




            for (Node vizinho: grafo.getAdjacentes(atual)) {
                if (!visitado.contains(vizinho)) {
                    fila.add(vizinho);
                    visitado.add(vizinho);

                    anterior.put(vizinho, atual);
                }
            }

        }

        List<Node> caminho = new ArrayList<>();
        atual = fim;

        while(atual != null){
            caminho.add(atual);
            atual = anterior.get(atual);
        }

        Collections.reverse(caminho);
        return caminho.getFirst().equals(inicio) ? caminho : Collections.emptyList();

    }
}
