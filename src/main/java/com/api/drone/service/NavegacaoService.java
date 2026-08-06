package com.api.drone.service;

import com.api.drone.algorithm.BuscaEmLargura;
import com.api.drone.enums.Direcao;
import com.api.drone.enums.Tipo;
import com.api.drone.model.DroneData;
import com.api.drone.model.Grafo;
import com.api.drone.model.Node;
import com.api.drone.model.Posicao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavegacaoService {
    private final BuscaEmLargura buscaEmLargura;
    private final Grafo grafo;
    public NavegacaoService(BuscaEmLargura buscaEmLargura, Grafo grafo) {
        this.buscaEmLargura = buscaEmLargura;
        this.grafo = grafo;
    }

    public Direcao movimentarDrone(DroneData data){

        Node atual = grafo.buscarNode(data.getX(), data.getY());
        if(atual == null){
           atual = new Node(data.getX(), data.getY(), Tipo.LIVRE,true);
            grafo.addNode(atual);
        } else {
            atual.setExplorado(true);
        }
        Node destino = grafo.buscarNode(data.getDestino().getX(), data.getDestino().getY());

        if(destino == null){
            destino = new Node(data.getDestino().getX(), data.getDestino().getY(), Tipo.LIVRE,false);
            grafo.addNode(destino);
        }

        for(Posicao posicao: data.getAdjacentes()){

            if(posicao.getTipo() == Tipo.OBSTACULO){
                continue;
            }

            Node vizinho = grafo.buscarNode(posicao.getX(), posicao.getY());

            if(vizinho == null){
               vizinho = new Node(posicao.getX(), posicao.getY(), posicao.getTipo(),false);
                grafo.addNode(vizinho);
            }
            if(!grafo.checkEdge(atual,vizinho)) {
                grafo.addEdge(atual, vizinho);
            }
        }

        List<Node> caminho = buscaEmLargura.buscaEmLargura(atual,destino);
        if(caminho.isEmpty()){
            Node exploracao = explorar(atual);

            if (exploracao != null) {
                return calcularDirecao(atual, exploracao);
            }
            else{
                return Direcao.SEM_CAMINHO;
            }
        }
        if(caminho.size() == 1){
            return Direcao.CHEGOU;
        }


        Node proximo = caminho.get(1);

        return calcularDirecao(atual, proximo);

    }



    private Direcao calcularDirecao(Node atual, Node proximo){
        if(proximo.getX() > atual.getX()) return Direcao.LESTE;
        if(proximo.getX() < atual.getX()) return Direcao.OESTE;
        if(proximo.getY() > atual.getY()) return Direcao.SUL;
        else return Direcao.NORTE;
    }

    public Node explorar(Node atual){

        for(Node vizinho: grafo.getAdjacentes(atual)){
            if(!vizinho.isExplorado() && vizinho.getTipo() == Tipo.LIVRE){
                return vizinho;
            }
        }
        return null;
    }
}
