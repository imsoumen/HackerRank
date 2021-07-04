public Difference(int[] elements){
        this.elements = elements;
        }

public int computeDifference(){
        maximumDifference = elements[1] - elements[0];
        for( int i = 0; i < elements.length; i++ ){
        for(int j=0;j<elements.length;j++){
        if((elements[j] - elements[i]) > maximumDifference)
        {
        maximumDifference = (elements[j] - elements[i]);
        }
        }
        }
        return maximumDifference;
        }