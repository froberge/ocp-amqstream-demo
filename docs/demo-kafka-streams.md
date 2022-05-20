# Démonstration Kafka Streams

## Opération possible avec Kafka Streams

* `Aggregation` Prend un topic ou une table d'entrée et génère une nouvelle table en combinant plusieurs enregistrements d'entrée en un seul enregistrement de sortie. Des exemples d'agrégations sont le calcul de comptages ou de sommes.

* `Join` Fusionne deux topic et/ou tables d'entrée en fonction des clés de leurs enregistrements de données et produit un nouveau topic/table.

* `Windowing` Regroupez les topics qui ont la même clé par fenêtre horaire. Par exemple, vous pouvez regrouper les événements qui se sont produits entre une période donnée.

`Ineractive Queries` traite le stream comme un une base de données intégrée légère, pour interroger directement le dernier état de votre application de stream.

---

## Application de opération aggregation

Dans cette démo, nous allons construire une applications pour voir le nombre de fois qu'une chanson a été jouée, `music-chart-app`, ainsi qu'une application qui joue des chansons de façon périodique, `player-app`.

* Le `player-app` n'utilises pas Kafka Streams, il ne fait que produit sur 2 differents topics.  

* Le `music-chart-app` utilise Kafka Stream pour joindre les topics `songs` et `played-songs`, pour obtenir le nom de la chanson, et enfin, il met à jour le nombre de fois que la chanson a été jouée.


![kafka-streams](images/kafka%20streams.jpeg)


## Déploiement du player-app

* Créer le topic Kafka
    ```
    oc create -f manifest/kafka/songs.yaml
    ```

* Déploiement de l'application
    ```
    oc apply -f manifest/k8s/player-app/deployment.yaml
    ```
* Création du service
    ``` 
    oc apply -f manifest/k8s/player-app/service.yaml
    ```


* Accédons les logs
    ``` 
    stern "player-app-\w" -c player-app
    ```

    Résultat: on devrait voir les chansons défiler.

___

## Déploiement du music-chart-app

* Créer le topic Kafka
    ```
    oc create -f manifest/kafka/played-songs.yaml
    ```


* Déploiement de l'application
    ```
    oc apply -f manifest/k8s/music-chart-app/deployment.yaml
    ```
* Création du service
    ``` 
    oc apply -f manifest/k8s/music-chart-app/service.yaml
    ```


* Accédons les logs
    ``` 
    stern "music-chart-app-\w" -c music-chart-app
    ```

    Résultat: on devrait voir l'agggregation des chansons qui ont joué
___


## Test

1. Ouvrir un terminal connecter a OpenShift et accéder au logs du player-app
    ``` 
    stern "player-app-\w" -c player-app
    ```

1. Ouvrir un terminal connecter a OpenShift et accéder au logs du music-chart-app
    ``` 
    stern "music-chart-app-\w" -c music-chart-app
    ```

    Resultat: Pour chaque chansons jouer du coté player-app l'aggregation du côté de music-chart-app devrait monter.
---

:construction: __CLEAN UP__
```
./scripts/cleanup-demo2.sh
```
