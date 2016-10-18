int a;
float baaaa, c, d;

float switch_case() {
    float a, b[2];

    switch(a) {
        
        case 2: /*break;
        case 3:*/
        /*default:*/
        /*default: break;*/
    }
}
int main(){
   int studentNumber, count, i, sum;

    int mark[4];
    float average;

    count = 4;
    sum = 0;

    for (i =0; i < count; i=i+1) {
        mark[i] = i * 30;
        sum = sum + mark[i];    
        average = avg(i+1, mark);
        if (average > 40.0 ) {
            printf(1, average);
        }
    }
}