package com.api.drone.service;

import com.api.drone.algorithm.BuscaEmLargura;
import com.api.drone.enums.Acao;
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

    public Acao movimentarDrone(DroneData data){

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
            Node exploracao = explorar(atual, destino);

            if (exploracao != null) {
                return calcularAcao(atual, exploracao, data.getOrientacao());
            }
            else{
                return Acao.SEM_CAMINHO;
            }
        }
        if(caminho.size() == 1){
            return Acao.CHEGOU;
        }


        Node proximo = caminho.get(1);

        return calcularAcao(atual, proximo, data.getOrientacao());

    }



        private Direcao calcularDirecaoAbsoluta(Node atual, Node proximo){
        if (proximo.getX() > atual.getX()) {
            return Direcao.LESTE;
        }

        if (proximo.getX() < atual.getX()) {
            return Direcao.OESTE;
        }

        if (proximo.getY() > atual.getY()) {
            return Direcao.NORTE;
        }

        return Direcao.SUL;
    }


    private Acao calcularAcao(Node atual, Node proximo, Direcao orientacao){

        Direcao destino = calcularDirecaoAbsoluta(atual, proximo);

        if(destino == orientacao){
            return Acao.FRENTE;
        }
        if(destino == oposta(orientacao)){
            return Acao.TRAS;
        }
        if(destino == direita(orientacao)){
            return  Acao.DIREITA;
        }
        else{
            return  Acao.ESQUERDA;
        }
    }

    public Node explorar(Node atual, Node destino){
            Node melhorVizinho = null;
            int menorDistancia = Integer.MAX_VALUE;
        for(Node vizinho: grafo.getAdjacentes(atual)){
            if(!vizinho.isExplorado() && vizinho.getTipo() == Tipo.LIVRE){
                int distancia = Math.abs(vizinho.getX() - destino.getX()) +
                                Math.abs(vizinho.getY() - destino.getY());
                if(distancia < menorDistancia){
                    menorDistancia = distancia;
                    melhorVizinho = vizinho;
                }
            }
        }
        return melhorVizinho;
    }

    private Direcao oposta (Direcao direcao){

        switch(direcao){
            case NORTE:
                return Direcao.SUL;
            case SUL:
                return Direcao.NORTE;
            case LESTE:
                return Direcao.OESTE;
            case OESTE:
                return Direcao.LESTE;
            default:
                return direcao;
        }

    }

    private Direcao direita(Direcao direcao){

        switch(direcao){
            case NORTE:
                return Direcao.LESTE;

            case LESTE:
                return Direcao.SUL;

            case SUL:
                return Direcao.OESTE;

            case OESTE:
                return Direcao.NORTE;

            default:
                return direcao;
        }
    }
}
