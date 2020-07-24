/*
 * Hi Hatta's here!! Enjoy my code!
 * Feel free to Approach me if you have anything to ask
 * hattaldino@gmail.com
 */

package SummaryStatistic;

/*
 * @author hattaldino
 */
public class summary_statistic {
    public static float median(float[] data){
        sort(data);
        
        if(isEven(data)){
            int inLeft = data.length / 2;
            int inRight = (data.length / 2) + 1;
            float midData = data[inLeft - 1] + data[inRight - 1];
            return (float)midData/2;
        }
        else {
            int midIndex = (data.length + 1) / 2;
            return data[midIndex - 1];
        }
    }
    
    private static boolean isEven(float[] data){
        return data.length % 2 == 0;
    }
    
    public static void sort(float[] data){
        for(int idx = 0; idx < data.length - 1; idx++){
            int minIdx = idx;
            for(int v = idx + 1; v < data.length; v++){
                if(data[minIdx] > data[v]){
                    minIdx = v;
                }
            }
            
            float temp = data[minIdx];
            data[minIdx] = data[idx];
            data[idx] = temp;
        }
    }
    
    public static float range(float[] data){
        return data[findMax(data)] - data[findMin(data)];
    }
    
    public static int findMax(float[] data){
        int max = 0;
        for (int i = 1; i < data.length; i++){
            if(data[i] > data[max])
                max = i;
        }
        
        return max;
    }
    
    public static int findMin(float[] data){
        int min = 0;
        for (int i = 1; i < data.length; i++){
            if(data[i] < data[min])
                min = i;
        }
        
        return min;
    }
    
    public static float modus(float[] data){
        float[] valueRecord = new float[0];
        float[] totalforEachValue = new float[0];
        float keepValue;
        int maxIdx = 0;
        
        for(int i = 0; i < data.length; i++){
            keepValue = data[i];
            if(valueRecord.length == 0){
                appendArr(valueRecord, keepValue);
                appendArr(totalforEachValue, 1);
            } 
            else {
                if(isValueExist(valueRecord, keepValue)){
                    int index = findIndex(valueRecord, keepValue);
                    totalforEachValue[index]++;
                }
                else {
                    appendArr(valueRecord, keepValue);
                    appendArr(totalforEachValue, 1);
                }
            }
        }
        
        for(int j = 1; j < totalforEachValue.length; j++){
            if(totalforEachValue[j] > totalforEachValue[maxIdx]){
                maxIdx = j;
            }
        }
        
        return valueRecord[maxIdx];
    }
    
    private static boolean isValueExist(float[] valueRecord, float value){
        for(int i = 0; i < valueRecord.length; i++){
            if(valueRecord[i] == value){
                return true;
            }
        }
        return false;
    }
    
    public static int findIndex(float[] valueRecord, float value){
        int i = 0;
        while(valueRecord[i] != value){
            i++;
        }
        return i;
    }
    
    private static float[] appendArr(float[] data, float value){
        int length = data.length + 1;
        float[] newRecord = new float[length];
        System.arraycopy(data, 0, newRecord, 0, data.length);
        newRecord[length - 1] = value;
        return newRecord;
    }
    
    public static float[] quartil(float[] data){
        sort(data);
        float[] quartilValue = new float[3];
        
        if((data.length % 2 != 0) && ((data.length+1) % 4 == 0)){
            quartilValue[0] = data[(data.length+1) / 4];
            quartilValue[1] = data[(2 * (data.length+1)) / 4];
            quartilValue[2] = data[(3 * (data.length+1)) / 4];
        }
        else if((data.length % 2 != 0) && ((data.length+1) % 4 != 0)){
            float numerator1 = data[(data.length-1) / 4] + data[(data.length+3) / 4];
            quartilValue[0] = numerator1 / 2;
            
            quartilValue[1] = data[(2 * (data.length+1)) / 4];
            
            float numerator3 = data[(3*data.length + 1) / 4] + data[(3*data.length + 5) / 4];
            quartilValue[2] = numerator3 / 2;
        }
        else if((data.length % 2 == 0) && ((data.length) % 4 == 0)){
            float numerator1 = data[data.length / 4] + data[data.length / 4 + 1];
            quartilValue[0] = numerator1 / 2;
            
            float numerator2 = data[data.length / 2] + data[data.length / 2 + 1];
            quartilValue[1] = numerator2 / 2;
            
            float numerator3 = data[(3*data.length) / 4] + data[(3*data.length) / 4 + 1];
            quartilValue[2] = numerator3 / 2;
        }
        else if((data.length % 2 == 0) && ((data.length) % 4 != 0)){
            quartilValue[0] = data[(data.length + 2) / 4];
            
            float numerator2 = data[data.length / 2] + data[data.length / 2 + 1];
            quartilValue[1] = numerator2 / 2;
            
            quartilValue[2] = data[(3*data.length + 2) / 4];
        }
        else {
            quartilValue[0] = -1;
            quartilValue[1] = -1;
            quartilValue[2] = -1;
        }
        
        return quartilValue;
    }
    
    public static float arithmeticMean(float[] data){
        float total = 0;
        for(int i = 0; i < data.length; i++){
            total += data[i];
        }
        
        return total / data.length;
    }
    
    public static float harmonicMean(float[] data){
        float total = 0;
        for(int i = 0; i < data.length; i++){
            total += 1 / data[i];
        }
        
        return data.length / total;
    }
    
    public static float geometricMean(float[] data){
        float total = 1;
        for(int i = 0; i < data.length; i++){
            total *= data[i];
        }
        
        return root(total, data.length);
    }
    
    public static float root(float value, int exp){
        float start = 0;
        float end = value;
        float e = 0.000001f;
        
        while(true){
            float mid = (start + end) / 2;
            float error = diff(mid, value, exp);
            
            if(error <= e){
                return mid;
            }
            else{
                if(exponent(mid, exp) < value){
                    start = mid;
                }
                else{
                    end = mid;
                }
            }
        }
        
    }
    
    public static float exponent(float value, int exp){
        if(exp == 0){
            return 1;
        }
        else
            return value * exponent(value, exp--);
    }
    
    private static float diff(float mid, float value, int exp){
        float exponent = exponent(mid, exp);
        
        if(value > exponent)
            return value - exponent;
        else
            return exponent - value;
    }
    
    public static float weightedMean(float[] data, float[] weight){
        float weightedData = 0;
        float totalWeight = 0;
        for(int i = 0; i < data.length; i++){
            weightedData += data[i] * weight[i];
            totalWeight += weight[i];
        }
        
        return weightedData / totalWeight;
    }
    
    public static float standardDeviation(float[] data){
        float mean = arithmeticMean(data);
        float diff = 0;
        float variance;
        
        for(int i = 0; i < data.length; i++){
            diff += exponent((data[i] - mean), 2);
        }
        
        variance = diff / (data.length - 1);
        return root(variance, 2);
    }
}
