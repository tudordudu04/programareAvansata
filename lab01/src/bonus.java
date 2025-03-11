public class bonus {
    public static void printMatrix(int[][] matrix, int[] nr) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println("nr vecici: " + nr[i]);
        }
    }

    public static boolean areAdjacent(int[] x, int[][] adjMatrix) {
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x.length; j++) {
                int aux = adjMatrix[x[i]][x[j]];
                if (aux != 1 && x[i] != x[j])
                    return false;
            }
        return true;
    }

    public static boolean OK(int[] x, int k){
        for(int i = 0 ; i < k ; ++ i)
            if(x[k] == x[i])
                return false;
        return true;
    }
    public static boolean genCombinations(int initialSize, int cliqueSize, int[] neighbours, int[][] matrix, int[] x)
    {
        for(int i = 0; i < neighbours.length ; ++ i)
        {
            x[initialSize] = neighbours[i];
            if(OK(x, initialSize))
                if(initialSize == cliqueSize - 1) {
                    if(areAdjacent(x, matrix)){
                        for (int j = 0; j < cliqueSize; ++j)
                            System.out.print(x[j] + " ");
                        System.out.println();
                        return true;
                    }
                }
                else
                    if(genCombinations(initialSize+1, cliqueSize, neighbours, matrix, x))
                        return true;
        }

        return false;
    }

    public static boolean isClique(int row, int cliqueSize, int[][] matrix) {
        int column, nr = 0;
        for(column = 0; column < matrix[row].length; column++)
            if(matrix[row][column] == 1)
                nr++;
        nr ++;
        int[] neighbours = new int[nr];
        for(column = matrix[row].length-1; column >=0; column--)
            if(matrix[row][column] == 1 || row == column){
                neighbours[nr-1] = column;
                nr--;
            }
        int[] verifyArray = new int[cliqueSize];
        if(genCombinations(0, cliqueSize, neighbours, matrix, verifyArray))
            return true;
        return false;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Didn't provide exactly one argument");
            System.exit(-1);
        }
        int cliqueSize = Integer.parseInt(args[0]);
        int nrNodes;
        do {
            nrNodes = (int) (Math.random() * 100);
        } while (nrNodes < (cliqueSize*2) || nrNodes > 40);

        int[][] adjMatrix = new int[nrNodes][nrNodes];
        int i, j;

        for (i = 0; i < nrNodes; i++) {
            adjMatrix[i][i] = 0;
            for (j = i + 1; j < nrNodes; j++) {
                adjMatrix[i][j] = (int) (Math.random() * 10) % 2;
                adjMatrix[j][i] = adjMatrix[i][j];
            }
        }


        int[] nrAdjacentNodes = new int[nrNodes];
        for (i = 0; i < nrNodes; i++) {
            nrAdjacentNodes[i] = 0;
            for (j = 0; j < nrNodes; j++) {
                if (adjMatrix[i][j] == 1) {
                    nrAdjacentNodes[i]++;
                }
            }
        }
        printMatrix(adjMatrix, nrAdjacentNodes);

        for (i = 0; i < nrNodes; i++) {
            if(nrAdjacentNodes[i] >= cliqueSize - 1){
                if(isClique(i, cliqueSize, adjMatrix)) {
                    System.out.println("A clique has been found.");
                    break;
                }
            }
        }
    }
}

