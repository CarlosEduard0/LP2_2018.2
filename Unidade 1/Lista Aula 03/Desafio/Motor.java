public class Motor {

    public enum TipoCombustivel {
        ALCOOL, GASOLINA, FLEX
    }

    private TipoCombustivel tipoCombustivel;
    private float cilindradas;
    private boolean ligado;

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public float getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(float cilindradas) {
        this.cilindradas = cilindradas;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }
}
