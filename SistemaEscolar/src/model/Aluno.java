package model;

public class Aluno {
    private int id;
    private String nome;
    private String dt_nascimento;
    private String matricula;

    public Aluno(String nome, String matricula, String dt_nascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dt_nascimento = dt_nascimento;
    }

    public Aluno() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }


    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dt_nascimento='" + dt_nascimento + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }


}
