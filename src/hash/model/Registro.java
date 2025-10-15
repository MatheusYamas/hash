package hash.model;

public class Registro {
    private int codigo;

    // verificação para limite de 9 digitos
    public Registro(int codigo) {
        if (String.valueOf(codigo).length() > 9) {
            this.codigo = Integer.parseInt(String.valueOf(codigo).substring(0, 9));
        } else {
            this.codigo = codigo;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    // imprimir de forma mais legível
    @Override
    public String toString() {
        return String.format("%09d", codigo);
    }
}
