import java.io.*;
import java.util.*;
import java.nio.charset.*;
import java.text.DateFormat;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

class Musica {

    private String id = "";
    private String nome = "";
    private String key = "";
    private List<String> artists = new ArrayList<String>();
    private Date release_date = new Date();
    private double acousticness = 0.0;
    private double danceability = 0.0;
    private double energy = 0.0;
    private int duration_ms = 0;
    private double instrumentalness = 0.0;
    private double valence = 0.0;
    private int popularity = 0;
    private float tempo = 0;
    private double liveness = 0.0;
    private double loudness = 0.0;
    private double speechiness = 0.0;
    private int year = 0;

    //Construtores
    public Musica() {
        this.id = ""; 
        this.nome = ""; 
        this.key = ""; 
        this.artists =  new ArrayList<String>();
        this.release_date = new Date(); 
        this.acousticness = 0.0; 
        this.danceability = 0.0; 
        this.energy = 0.0; 
        this.duration_ms = 0; 
        this.instrumentalness = 0.0; 
        this.valence = 0.0; 
        this.popularity = 0; 
        this.tempo = 0; 
        this.liveness = 0.0; 
        this.loudness = 0.0; 
        this.speechiness = 0.0; 
        this.year = 0; 
    }

    public Musica( String id, String nome, String key, String[] artists, Date date,
    double acousticness, double danceability, double energy, int duration_ms, double instrumentalness, 
    double valence, double liveness, double loudness, double speechiness, int year, int popularity, float tempo ) {
        this.id = id; 
        this.nome = nome; 
        this.key = key;
        this.artists =  new ArrayList<String>();
        for (String temp:artists) {
            this.artists.add(temp);
        }
        this.release_date = date; 
        this.acousticness = acousticness; 
        this.danceability = danceability; 
        this.energy = energy; 
        this.duration_ms = duration_ms; 
        this.instrumentalness = instrumentalness; 
        this.valence = valence; 
        this.popularity = popularity; 
        this.tempo = tempo; 
        this.liveness = liveness; 
        this.loudness = loudness; 
        this.speechiness = speechiness; 
        this.year = year; 
    }

    //Getters
    public String getId() {return id;}
    public String getNome() {return nome;}
    public String getKey() {return key;}
    public String getArtists() {
        String temp = "";
        if (this.artists.size() == 0) {
            temp = "Nao declarado";
        } else {
            temp = "[" + this.artists.get(0);
            for (int i = 1; i < this.artists.size(); i++) 
                temp = temp + ", " + this.artists.get(i);
            temp +="]";
        }
        return temp;
    }
    public Date getReleaseDate() {return release_date;}
    public double getAcousticness() {return acousticness;}
    public double getDanceability() {return danceability;}
    public double getEnergy() {return energy;}
    public int getDuration() {return duration_ms;}
    public double getInstrumentalness() {return instrumentalness;}
    public double getValence() {return valence;}
    public int getPopularity() {return popularity;}
    public float getTempo() {return tempo;}
    public double getLiveness() {return liveness;}
    public double getLoudness() {return loudness;}
    public double getSpeechiness() {return speechiness;}
    public int getYear() {return year;}

    //Setters
    public void setId(String id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setKey(String key) {this.key = key;}
    public void setArtists(String artist) {this.artists.add(artist);}
    public void setReleaseDate(Date release_date) {this.release_date = release_date;}
    public void setAcousticness(double acousticness) {this.acousticness = acousticness;}
    public void setDanceability(double danceability) {this.danceability = danceability;}
    public void setEnergy(double energy) {this.energy = energy;}
    public void setDuration(int duration_ms) {this.duration_ms = duration_ms;}
    public void setInstrumentalness(double instrumentalness) {this.instrumentalness = instrumentalness;}
    public void setValence(double valence) {this.valence = valence;}
    public void setPopularity(int popularity) {this.popularity = popularity;}
    public void setTempo(float tempo) {this.tempo = tempo;}
    public void setLiveness(double liveness) {this.liveness = liveness;}
    public void setLoudness(double loudness) {this.loudness = loudness;}
    public void setSpeechiness(double speechiness) {this.speechiness = speechiness;}
    public void setYear(int year) {this.year = year;}


    /**
     *  Metodo clone()
     */
    public Musica clone() {
        Musica resp = new Musica();
        resp.id = this.id;
        resp.nome = this.nome;
        resp.key = this.key;
        resp.artists = this.artists;
        resp.artists = this.artists;
        resp.release_date = this.release_date;
        resp.acousticness = this.acousticness;
        resp.danceability = this.danceability;
        resp.energy = this.energy;
        resp.duration_ms = this.duration_ms;
        resp.instrumentalness = this.instrumentalness;
        resp.valence = this.valence;
        resp.popularity = this.popularity;
        resp.tempo = this.tempo;
        resp.liveness = this.liveness;
        resp.loudness = this.loudness;
        resp.speechiness = this.speechiness;
        resp.year = this.year;
        return resp;
    }

    /**
     *  Metodo print() - printa principais atributos do objeto
     */
    public String print (){
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(this.release_date);

        return(this.getId()+" ## "+this.getArtists()+" ## "+this.getNome()+" ## "+
        (stringDate)+" ## "+this.getAcousticness()+" ## "+this.getDanceability()+
        " ## "+this.getInstrumentalness()+" ## "+this.getLiveness()+" ## "+this.getLoudness()+" ## "+
        this.getSpeechiness()+" ## "+this.getEnergy()+" ## "+this.getDuration());
    }

    /**
     *  Metodo read() - le string e a converte em atributos, que sao respectivamente adicionados
     *  a um objeto
     */
    public void read(String line)  throws Exception{

        //Primeiro separar a string em diferentes substring
        // 0 - Valence, 1 - Ano, 2 - Acoust., 3 - Artistas, 4 - Dancea.,
        // 5 - Duration, 6 - Energy, 8 - ID, 9 - Instrumentalness, 10 - Key
        //  11 - Levin., 12 - loudn., 14 - Nome, 15 - Pop., 16 - Date, 17 - Speech., 18 - Tempo 
        String reg [] = new String[19];
        int j = 0;
        char del = '\0';
		String temp = "";
		for (int i = 0; i < 19; i++) {
            del = '\0';
            del = line.charAt(j) == 34 ? line.charAt(j) : del;
			temp = "";
			while (j < line.length() && ((line.charAt(j) != 44) 
                || !(line.charAt(j) == 44 && line.charAt(j + 1) != 32) || 
                    (line.charAt(j - 1) != del && del != '\0'))) {
				if (line.charAt(j) != 34)
					temp += line.charAt(j);
                else if (line.charAt(j) == 34 && line.charAt(j+1) == 34)
                    temp += line.charAt(j++);
				j++;
			}
			j++;
			reg[i] = temp;
		}

        setValence(myAtof(reg[0]));
        setYear(myAtoi(reg[1]));
        setAcousticness(myAtof(reg[2]));
        setDanceability(myAtof(reg[4]));
        setDuration(myAtoi(reg[5]));
        setEnergy(myAtof(reg[6]));
        setId(reg[8]);
        setInstrumentalness(myAtof(reg[9]));
        setKey(reg[10]);
        setLiveness(myAtof(reg[11]));
        setLoudness(myAtof(reg[12]));
        setNome(reg[14]);
        setPopularity(myAtoi(reg[15]));
        setSpeechiness(myAtof(reg[17]));
        setTempo((float) myAtof(reg[18]));
        prepArt(reg[3]);
        
        Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(prepDate(reg[16]));
        setReleaseDate(date1); 

    }
    
    /**
     *  Metodo myTrim() - "trima" uma string que comece com '"' ou '''
     *  @return - string sem os delimitadores 
     */
    public String myTrim(String str){
        String temp = "";
        char del = '_';
        int i = 0, j = str.length()-1;

        while (str.charAt(i) == 32) 
            i++;
        
        if(str.charAt(i) == 34 || str.charAt(i) == 39 )
            del = str.charAt(i++);

        while (str.charAt(j) == 32 || str.charAt(j) == del) 
            j--;
        
        for(; i <=j; i++)
            temp+= str.charAt(i);

        return temp;
    }

    /**
     *  Metodo prepArt() - Separa os diferentes artistas que ate entao estavam em uma unica string
     */
    public void prepArt(String artists){
        String temp = "";
        for(int i = 0; i < artists.length(); i++){
            if (artists.charAt(i) == 44 && artists.charAt(i+1) == 32 && artists.charAt(i-1) == 39) {
                i++;
                setArtists(myTrim(temp));
                temp="";
            }else if (artists.charAt(i) != 91 && artists.charAt(i) != 93) 
                temp += artists.charAt(i);
                
        }
        setArtists(myTrim(temp));
    }

    /**
     *  Metodo prepDate() - Nos casos onde a string de data nao esta completa, ex: "1921", o metodo
     *  compelta  a string e a retorna
     */
    public String prepDate(String date){
        String comp = "2021-01-01";
        String temp = "";
        char aux = '-';
        for (int i = 0; i < date.length(); i++) {
            aux = date.charAt(i);
            if((int)aux >= 48 && (int)aux <= 57)
                temp += aux;
            else
                temp += comp.charAt(i);
        }
        for (int i = (date.length()); i < comp.length(); i++) {
            temp += comp.charAt(i);
        }
        return temp;
    }

    public static int myAtoi(String str){
        int res = 0;
        for (int i = 0; i < str.length(); ++i)
            res = res * 10 + str.charAt(i) - '0';
        //Para evitar erro vou utilizar metodo da classe Integer, mas o algoritmo e funcional
        //return res;
        return Integer.parseInt(str);
    }

    public static double myAtof(String str) {
        double res = 0.0;
        double dec = 0.0;
        double valid = 1.0;
        double sign = 1.0;
        int i = 0;
        int m = 0;

        //tirar espaços 
        while(((int)str.charAt(i)) == 32)
            i++;
        //Declarar qual o sinal
        if(((int)str.charAt(i)) == 45 ){
            i++;
            sign = -1.0;
        }
        //calcular parte inteira
        for (; i < str.length() && ((int)str.charAt(i)) != 46 && valid == 1.0; ++i){
            if(((int)str.charAt(i)) >= 48 && ((int)str.charAt(i)) <= 57)
                res = res * 10 + str.charAt(i) - '0';
            else
                valid = 0.0;
        }
        i++;
        //calcular parte decimal
        for(m = i; m < str.length() && valid == 1.0; m++){
            if(((int)str.charAt(m)) >= 48 && ((int)str.charAt(m)) <= 57)
                dec = dec * 10 + str.charAt(m) - '0';
            else if(((int)str.charAt(m)) == 32)
                m = str.length();
            else
                valid = 0.0;
        }
        //Metodo funciona, com excessao de numero com notacao cientifica, po isso uso clas Double
        //return sign*valid*(res + (Math.pow(10, i-m) * dec));
        return Double.valueOf(str);
    }


}

public class Main {
    public static int countComp = 0;
    public static int countMov = 0;

    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void swap(int i, int j, Musica[] entrada) {
        Musica temp = entrada[i];
        entrada[i] = entrada[j];
        entrada[j] = temp;
        countMov += 3;
    }

    public static Musica[] heapsort(Musica[] array, int n) {  
        Musica[] tmp = new Musica[n+1];
        for(int i = 0; i < n; i++){
            tmp[i+1] = array[i];
            countMov++;
        }
        array = tmp;

        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++){
            construir(tamHeap, array);
        }

        //Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
            swap(1, tamHeap--, array);
            reconstruir(tamHeap, array);
        }

        //Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new Musica[n];
        for(int i = 0; i < n; i++){
            array[i] = tmp[i+1];
            countMov++;
        }
        return array;
    }

    public static void construir(int tamHeap, Musica[] array){
        for(int i = tamHeap; i > 1 && compareSongs(array[i], array[i/2]) > 0 ; i /= 2){
            swap(i, i/2, array);
            countComp++;
        }
    }

    public static void reconstruir(int tamHeap, Musica[] array){
        int i = 1;
        while(i <= (tamHeap/2)){
            int filho = getMaiorFilho(i, tamHeap, array);
            if(compareSongs(array[i],array[filho]) < 0){
                countComp++;
                swap(i, filho, array);
                i = filho;
            }else{
                countComp++;
                i = tamHeap;
            }
        }
    }

    public static int getMaiorFilho(int i, int tamHeap, Musica[] array){
        int filho;
        if (2*i == tamHeap || compareSongs(array[2*i],array[2*i+1]) > 0){
            filho = 2*i;
        } else {
            filho = 2*i + 1;
        }
        countComp++;
        return filho;
    }

    public static int compareSongs(Musica song1, Musica song2){
        int resp = 0;
        if(song1.getReleaseDate().compareTo(song2.getReleaseDate()) > 0)
            resp = 1;
        else if(song1.getReleaseDate().compareTo(song2.getReleaseDate()) < 0)
            resp = -1;
        else if (song1.getReleaseDate().compareTo(song2.getReleaseDate()) == 0){
            if(song1.getNome().compareTo(song2.getNome()) > 0)
                resp = 1;
            else if (song1.getNome().compareTo(song2.getNome()) < 0)
                resp = -1;
            else 
                resp = 0;
        }
        return resp;
    }

    public static void main(String[] args) throws Exception{
        String line = "";
        String val[] = new String[500];
        int i = 0;
        //Capturar todas as IDs da entrada padrao
        try {
            val[i] = MyIO.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        while (!isFim(val[i])) {
            i++;
            try {
                val[i] = MyIO.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        BufferedReader br = new BufferedReader(new FileReader("/tmp/data.csv"));
        Musica[] entrada = new Musica[i];
        for(int k = 0; k < i; k++){
            br = new BufferedReader(new FileReader("/tmp/data.csv"));;
            while(!line.contains(val[k])){
                line = br.readLine();
            }
            entrada[k] = new Musica();
            entrada[k].read(line);
        }
        
        double start = System.currentTimeMillis();

        entrada = heapsort(entrada, entrada.length);

        for(int k = 0; k< entrada.length; k++){
            System.out.println(entrada[k].print());
        }

        FileWriter myWriter = new FileWriter("matrícula_heapsort.txt");
        myWriter.write("711260\t" + countComp+"\t"+countMov+"\t"+((System.currentTimeMillis()-start)/1000) );
        myWriter.close();
        
         
    }

}