package ZonaBot;

public class testone {

    public static void main(String[] args) {

        int[] primo = {3,2,6,4,9,1,4};
        int [] secondo= new int[primo.length];
        int temp=0;
        int min=0;

        for(int i=0; i<primo.length; i++){
            for(int j=0; j< primo.length;j++){
                temp=primo[j];
                if(temp>primo[i]){
                    min=primo[i];
                }else {
                    min=temp;
                }

            }
            secondo[i]=temp;
        }
        secondo[secondo.length-1]=min;

        System.out.println("[");
        for(int i=0; i<primo.length; i++){
            System.out.print(primo[i]);
        }
        System.out.println("]");


    }
}
