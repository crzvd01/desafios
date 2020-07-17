package idwall.desafio.string;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text, Integer limit, Boolean justify) {
    	String format = "";
    	
    	//separa os paragrafos do texto
    	String[] paragrafos = text.split("\\n\\n");
    	
    	for (String paragrafo : paragrafos) {
    		
    		StringBuilder sb = new StringBuilder(paragrafo);

    		int i = 0;
    		
    		while (i + limit < sb.length() && (i = sb.lastIndexOf(" ", i + limit)) != -1) {
    		    sb.replace(i, i + 1, "\n");
    		}
    		if(justify) {
    			format = format.concat(justificar(sb.toString(), limit) + "\n");
    		}else {
    			format = format.concat(sb.toString() + "\n\n");
    		}
        }
    	
        return format;
    }
    
    public String justificar(String text, Integer limit) {
    	//separa paragrafos em linhas
    	String[] linhas = text.split("\\n");
    	Long espacos;
    	Integer diferenca=0;
    	Integer espaco = 0;
    	Integer espacoPlusOne = 0;
    	StringBuilder textoJustificado = null;
    	StringBuilder sb = new StringBuilder();
    	
    	
    	for (String linha : linhas) {
    		textoJustificado = new StringBuilder(linha);
    		String lacuna="";
    		//numero de lacunas entre as palavras da linha
    		espacos = linha.chars().filter(ch -> ch == ' ').count();
    		//a diferenca eh o numero de espacos a serem adicionados a string para dar o limite 
    		diferenca = limit - linha.length();	
    		//numero de espacos a serem colocados em cada lacuna
    		if(diferenca != 0) {
    			espaco= (int) ( diferenca / espacos) ;
        		espacoPlusOne = (int) (diferenca % espacos) ;

        		int nIteracoes = 0;
        		int index= textoJustificado.indexOf(" ");
        		int flagPlusOne = 0;
        		
        		for (int i = 0; i < espaco ;i ++) {
        			lacuna+= " ";
        		}
        		
        		while(index >= 0 && nIteracoes < diferenca) {
        			
        			if(flagPlusOne < espacoPlusOne  && (nIteracoes%2) == 0 ) {
        				textoJustificado.insert(index, lacuna+ " ");
        				index = textoJustificado.indexOf(" " , index+1 + espacoPlusOne );
        				flagPlusOne++;
        			}
        			else if(espaco == 0){
        				textoJustificado.insert(index, lacuna+ " ");
        				index = textoJustificado.indexOf(" " , index+1 +espacoPlusOne  );
        			}
        			else {
        				textoJustificado.insert(index, lacuna);
        				index = textoJustificado.indexOf(lacuna, index+1 + espaco );
        			}

        			nIteracoes++;
        		}
        		
    		}
    		sb.append(textoJustificado);
    		sb.append("\n");

    	} 
    	return sb.toString();
    }
}
