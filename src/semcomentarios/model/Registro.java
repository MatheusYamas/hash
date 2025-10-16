package semcomentarios.model;

public class Registro {
    private int codigo;
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

    @Override
    public String toString() {
        return String.format("%09d", codigo);
    }
}
