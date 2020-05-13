/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Random;

/**
 *
 * @author ROSMELINA
 */
public class GeneticAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static int[][] Pop(int ukPopulasi, int PanjangK) {
        Random ra = new Random();
        int[][] Pop = new int[ukPopulasi][PanjangK];
        for (int i = 0; i < ukPopulasi; i++) {
            for (int j = 0; j < PanjangK; j++) {
                Pop[i][j] = ra.nextInt(2);
            }
        }
        return Pop;
        
    }

    
    public static double[] decode(int[] Kromosom, int PanjangK){
        double[] deco = new double[PanjangK];
        int TitikPotong = PanjangK / 2;
        double on1 = 0;
        double on2 = 0;
        double under = 0;
        for (int k = 0; k < TitikPotong; k++) {
            on1 = on1+(Kromosom[k]*Math.pow(2, -(k+1)));
            on2 = on2+(Kromosom[k+TitikPotong]*Math.pow(2, -(k+1)));
            under = under+Math.pow(2, -(k+1));
        }
        double x1 = -3+(((3-(-3))/under)*on1);
        double x2 = -2+(((2-(-2))/under)*on2);
        deco[0] = x1;
        deco[1] = x2;   
        return deco;
        
    }
    
    
    public static double[] Fitness(int[][] Pop){
        double[] Fit = new double[Pop.length];
        for (int i = 0; i < Pop.length; i++) {
            double[] dec = decode(Pop[i],Pop[i].length);
            double x1 = dec[0];
            double x2 = dec[1];
            double z = (4-(2.1*Math.pow(x1, 2))+(Math.pow(x1, 4)/3))*Math.pow(x1, 2)+(x1*x2)+(-4+(4*Math.pow(x2, 2)))*Math.pow(x2, 2);
            Fit[i] = -z;
        }
        return Fit;
    }
    
    public static int ParS(double[] Fit){
        double TFit = 0;
        for (int i = 0; i < Fit.length; i++) {
            TFit += Fit[i];
        }
        Random ra = new Random();
        double Random = ra.nextDouble();
        int index = 0;
        double p = 0;
        while (p < Random){
            p += Fit[index]/TFit;
            index += 1;
        }
        return index;
    }
    public static int[][]CrossOver(int[][] Pop, double[] Fit, int PanjangK, double ProCross){
        int[][] child = new int[2][];
        int indexParent = 0;
        int indexParent2 = 0; 
        child[0] = Pop[indexParent].clone();
        child[1] = Pop[indexParent2].clone();
        Random ra = new Random();
        if(ra.nextDouble() < ProCross){
            indexParent = ParS(Fit);
            indexParent2 = ParS(Fit);
            int cp = ra.nextInt(PanjangK);
            for (int i = cp; i < PanjangK; i++) {
                int temp = child[0][i];
                child[0][i] = child[1][i];
                child[1][i] = temp;
            }
        }
        return child; 
    }
    
    public static int[][] Mutation(int[][] child, int PanjangK, double pm){
        Random ra = new Random();
        int[][] Mutation = child.clone();
        if (ra.nextDouble() < pm) {
            int ra1 = ra.nextInt(PanjangK);
            int ra2 = ra.nextInt(PanjangK);
            Mutation[0][ra1] = ra.nextInt(2);
            Mutation[1][ra2] = ra.nextInt(2);
        }
        return Mutation;
    }
    
    public static int surv(double[] Fit){
        int maximal = 0;
        for (int i = 0; i < Fit.length; i++) {
            if(Fit[i] > Fit[maximal]){
                maximal = i;
        }
       
    }
        return maximal;
    }
    
    public static int[][] GeneralReplace(int[][] PopOld, int[][] PopNew, double[] Fit){
        int[][] newPop = new int[PopOld.length][30];
        newPop[0] = PopOld[surv(Fit)].clone();
        for (int i = 1; i < PopNew.length; i++) {
            newPop[i] = PopNew[i].clone();
        }
        return newPop;
    }
    
    
//     public static void main(String[] args) {
//        int ukPopulasi = 8;
//        int PanjangK = 20;
//        int[][] Pop = Pop(ukPopulasi, PanjangK);
//        
//        for (int i = 0; i < ukPopulasi; i++) {
//            for (int j = 0; j < PanjangK; j++) {
//                System.out.print(Pop[i][j]);
//                
//        
//            }
//            System.out.println("");    
//        }
//        double[][] decode = x(Pop, PanjangK);
//        for (int i = 0; i < ukPopulasi; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.print(decode[i][j]);
//            }
//            System.out.println("");
//        }
//        double[] Fit = Fitness(decode);
//        for (int i = 0; i < decode.length; i++) {
//            System.out.println(Fit[i]);
//        }
//        System.out.println("");
//        
//        int parent = ParS(Fit);
//        System.out.println(parent);
//        
//        int[][] CrossOver = new int[2][PanjangK];
//        double ProCross = 0.8;
//        CrossOver = CrossOver(Pop, Fit, PanjangK, ProCross);
//       
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < PanjangK; j++) {
//                System.out.print(CrossOver[i][j]);
//            }
//            System.out.println("");
//                
//          }
//        double pm = 1;
//        int[][] M = Mutation(CrossOver, PanjangK, pm);
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < PanjangK; j++) {
//                    System.out.print(M[i][j]);
//                }
//                System.out.println("");
//        }
//        int maximal = surv(Fit);
//        System.out.println(maximal);
//    }
//    
public static void main(String[] args) {
        int ukPopulasi = 100;
        int PanjangK = 30;
        double ProCross = 0.7;
        double pm = 0.7;
        int genetic = 100;
        int[][] Pop = Pop(ukPopulasi, PanjangK);
        double[] Fit = new double[ukPopulasi];
        
        for (int i = 0; i < genetic; i++) {
            int[][]NewPop = new int[ukPopulasi][PanjangK];
            int j = 0;
            while(j<ukPopulasi){
                Fit = Fitness(Pop);
                int[][]child = CrossOver(Pop.clone(),Fit.clone(),PanjangK,ProCross);
                child = Mutation(child.clone(), PanjangK, pm);
                NewPop[j++] = child[0].clone();
                NewPop[j++] = child[1].clone();
            }
            Pop = GeneralReplace(Pop, NewPop, Fit);
    }
        System.out.print("Kromosom Terbaik: ");
        for (int i = 0; i < PanjangK; i++) {
            System.out.print(Pop[surv(Fit)][i]+" ");   
        }
        System.out.println("");
        System.out.println("Decode: "+decode(Pop[surv(Fit)],PanjangK)[0]+". "+decode(Pop[surv(Fit)],PanjangK)[1]);
    }
        
}
   
