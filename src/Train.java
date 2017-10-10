import java.awt.image.BufferedImage;

public class Train {
    private double[] weights;

    public Train(String symbol, BufferedImage image, double[] weights){
        this.weights=weights;
        double[] deltaW=new double[image.getHeight()*image.getWidth()];

        LetterReconning letterReconning=new LetterReconning();
        int[] train=letterReconning.getLetterArray(image);

        double majorError = 1;
        int desiredOutput;
        double quadraticError;

        double minimError = 0.001;
        while (majorError > minimError)
        {
            majorError = 0;
            String[] symbolList = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"
                    , "U", "V", "W", "X", "Y", "Z"};
            for (String aSymbolList : symbolList) {
                if (symbol.equals(aSymbolList))//DEFINE A SAIDA DESEJADA
                {
                    desiredOutput = 1;
                } else {
                    desiredOutput = -1;
                }
                double somatory = 0;
                for (int k = 0; k < image.getHeight() * image.getWidth(); k++)//-----------calculo de somatorio
                {
                    somatory += (train[k] * this.weights[k]);
                }
                if (somatory >= 0) {
                    somatory = 1;
                } else {
                    somatory = -1;
                }

                double erro = desiredOutput - somatory;//CALCULA O ERRO
                quadraticError = Math.pow(erro, 2) / 2;//CALCULA O ERRO QUADRATICO
                if (quadraticError > majorError) {
                    majorError = quadraticError;
                }//ATUALOZA O MAIOR ERRO Q.

                for (int j = 0; j < image.getWidth() * image.getHeight(); j++)//ATUALIZA OS PESOS DA LETRA
                {
                    double learningRate = 0.02;
                    deltaW[j] = erro * train[j] * learningRate;
                    this.weights[j] += deltaW[j];
                }
            }
        }
    }


    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }
}