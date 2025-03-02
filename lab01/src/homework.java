public class homework {
    public static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        if(args.length != 2){
            System.out.println("Didn't enter 2 arguments");
            System.exit(-1);
        }
        int nrNodes = Integer.parseInt(args[0]);
        int clique = Integer.parseInt(args[1]);
        int ok = 1;
        if((nrNodes + 1)/2 < clique)
        {
            System.out.println("Error: clique too large.");
            ok = 0;
        }
        int edges = 0, edgesPerNode;
        int maxDegree = 0, minDegree = nrNodes -1;
        int sumOfDegrees = 0;
        int aux;
        int[][] adjMatrix = new int[nrNodes][nrNodes];

        for(int i = 0; i < nrNodes - 1; i++){
            adjMatrix[i][i] = 0;
            edgesPerNode = 0;
            for(int j = i+1; j < nrNodes; j++) {
                aux = (int) (Math.random() * 10) % 2;
                if(ok == 1) {
                    if (i < clique * 2 - 1 && j < clique * 2 - 1) {
                        if (j < clique && i < clique)
                            adjMatrix[i][j] = 1;
                        else if (i == 0 || j == 0 || (i >= clique && j >= clique))
                                adjMatrix[j][i] = 0;
                        else adjMatrix[i][j] = aux;
                    }
                    else adjMatrix[i][j] = aux;
                }
                else adjMatrix[i][j] = aux;
                edgesPerNode += adjMatrix[i][j];
                adjMatrix[j][i] = adjMatrix[i][j];
            }
            if(edgesPerNode > maxDegree){
                maxDegree = edgesPerNode;
            }
            if(edgesPerNode < minDegree){
                minDegree = edgesPerNode;
            }
            edges += edgesPerNode;
            sumOfDegrees += 2*edgesPerNode;
        }
        printMatrix(adjMatrix);
        System.out.println("Edges: " + edges);
        System.out.println("Δ(G): " + maxDegree);
        System.out.println("δ(G): " + minDegree);
        if(sumOfDegrees == 2 * edges){
            System.out.println("The sum of the degrees is equal to double the sum of the edges.");
        }

        long endTime = System.nanoTime();
        long runningTime = (endTime - startTime) / 1000000;

        System.out.println("For n of: " + nrNodes+ ", running time took: " + runningTime + "ms");
    }

}