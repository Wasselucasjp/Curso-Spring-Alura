package br.com.alura.forum.dto;

public class ErrorDeFormularioDTO {
    private String campo;
    private String erro;

    /**
     * @param campo
     * @param erro
     */
    public ErrorDeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    /**
     * @return the campo
     */
    public String getCampo() {
        return campo;
    }

    /**
     * @return the erro
     */
    public String getErro() {
        return erro;
    }

    
    
}
