package Oving8;

   public class Main {
        public static void main(String[] args) {
            String filPlassering = "C:\\Users\\Alber\\Documents\\TDAT2005 - Algortimer\\Oving8\\grafer\\vg3";

            Dijkstragraf dijkstragraf = new Dijkstragraf(filPlassering);
            dijkstragraf.dijkstra(1);
        }
    }
