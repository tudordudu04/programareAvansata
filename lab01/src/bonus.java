import java.util.ArrayList;
public class bonus {
    public static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
    if(args.length != 1)
    {
        System.out.println("Didn't provide exactly one argument");
        System.exit(-1);
    }
    int cliqueSize = Integer.parseInt(args[0]);

    int nrNodes;
    do{
        nrNodes =(int) (Math.random() * 100);
    } while(nrNodes < 9 || nrNodes > 40);

    int[][] adjMatrix = new int[nrNodes][nrNodes];
    int i, j;

    for(i = 0; i < nrNodes; i++){
        adjMatrix[i][i] = 0;
        for(j = i+1; j < nrNodes; j++){
            adjMatrix[i][j] = (int) (Math.random() * 10) % 2;
            adjMatrix[j][i] = adjMatrix[i][j];
        }
    }
    printMatrix(adjMatrix);
        int[] nrAdjecentNodes = new int[nrNodes];
    for(i = 0; i < nrNodes; i++){
        nrAdjecentNodes[i] = 0;
        for(j = 0; j < nrNodes; j++) {
            if (adjMatrix[i][j] == 1)
                nrAdjecentNodes[i]++;
        }
    }

    int hasClique = 0;
        for(int iterator = 0; iterator < nrNodes; iterator++){
            if(nrAdjecentNodes[iterator] < cliqueSize - 1)
                break;
            ArrayList<Integer> arrayAdjacentNodes = new ArrayList<>();
            arrayAdjacentNodes.add(iterator);
            for(j = 1; j < nrNodes; j++){
                if(adjMatrix[iterator][j] == 1)
                    if(nrAdjecentNodes[j] >= cliqueSize - 1)
                        arrayAdjacentNodes.add(j); // aici fac lista de vecini a nodului meu care au si ei peste k-1 vecini
            }
            if(arrayAdjacentNodes.size() < cliqueSize)
                break;
            while(arrayAdjacentNodes.size() >= cliqueSize)
            {
                ArrayList<Integer> copyAdjacent = (ArrayList<Integer>) arrayAdjacentNodes.clone();
                ArrayList<Integer> auxClique = new ArrayList<>();
                int nr = -2;
                while(copyAdjacent.size() >= cliqueSize)
                {
                    for(i = 0; i < cliqueSize; i++){
                        auxClique.add(copyAdjacent.get(i));
                    }
                    int ok = -1;
                    i = 1;
                    while(i < cliqueSize && ok == -1)
                    {
                        for(j = 0; j < auxClique.size(); j++)
                            if(adjMatrix[auxClique.get(i)][auxClique.get(j)] == 0 && auxClique.get(i) != auxClique.get(j)) {
                                ok = j;
                                if(nr == -2)
                                    nr = i;
                                break;
                            }
                        i++;
                    }
                    if(i == cliqueSize){
                        hasClique = 1;
                        break;
                    }
                    copyAdjacent.remove(ok);
                    auxClique.clear();
                }
                if(hasClique == 1)
                    break;
                arrayAdjacentNodes.remove(nr);
            }
            if(hasClique == 1)
                break;
        }
        if(hasClique == 1)
            System.out.println("The clique has been found");
        else System.out.println("A clique of size k doesn't exist.");
    }
}
