package Models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

public class Cliente {
    private final Integer idCliente;
    private final LocalDate dtCadastro;
    private String nome;
    private String sobrenome;
    private String documento;
    private String email;
    private String senha;
    private LocalDate dtNascimento;

    // REGEX PATTERNS
    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String DOCUMENTO_PATTERN = "\\d{11}";

    private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern DOCUMENTO_REGEX = Pattern.compile(DOCUMENTO_PATTERN);

    //CONSTRUTORES

    /** Construtor principal, recebe id e data de cadastro. */
    public Cliente(Integer idCliente, LocalDate dtCadastro) {
        this.idCliente = Objects.requireNonNull(idCliente, "ID não pode ser nulo");
        this.dtCadastro = Objects.requireNonNull(dtCadastro, "Data de cadastro não pode ser nula");
    }

    /** Construtor padrão: gera um ID aleatório e usa data de hoje para cadastro. */
    public Cliente() {
        this.idCliente = gerarId();
        this.dtCadastro = LocalDate.now();
    }

    //GERAÇÃO DE ID
    private int gerarId() {
        // TODO: idealmente o DB gera o ID para evitar duplicação em ambientes concorrentes
        return new Random().nextInt(1_000_000);
    }

    //MATCHERS

    /** Tenta validar e setar o email; retorna true se OK. */
    private boolean emailMatch(String emailAddress) {
        if (emailAddress == null || emailAddress.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        if (EMAIL_REGEX.matcher(emailAddress).matches()) {
            this.email = emailAddress;
            return true;
        }
        return false;
    }

    /** Tenta validar e setar o documento; retorna true se OK. */
    private boolean documentoMatch(String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento não pode ser vazio");
        }
        if (DOCUMENTO_REGEX.matcher(documento).matches()) {
            this.documento = documento;
            return true;
        }
        return false;
    }

    //VALIDADORES EXTERNOS

    public void validaEmail(String email) {
        if (!emailMatch(email)) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
    }

    public void validaDocumento(String documento) {
        if (!documentoMatch(documento)) {
            throw new IllegalArgumentException("Documento inválido (deve conter 11 dígitos).");
        }
    }

    public void validaSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        this.senha = senha;
    }

    /**
     * Valida se a idade ≥ 18 anos.
     * Se válido, seta dtNascimento; caso contrário, lança exceção.
     */
    public void validaIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula.");
        }
        LocalDate limite = LocalDate.now().minusYears(18);
        if (dataNascimento.isAfter(limite)) {
            throw new IllegalArgumentException("Menor de 18 anos não permitido.");
        }
        this.dtNascimento = dataNascimento;
    }

    //SETTERS

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setEmail(String email) {
        validaEmail(email);
    }

    public void setDocumento(String documento) {
        validaDocumento(documento);
    }

    public void setSenha(String senha) {
        validaSenha(senha);
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        validaIdade(dtNascimento);
    }

    //GETTERS

    public Integer getIdCliente() {
        return idCliente;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", documento='" + documento + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtNascimento=" + dtNascimento +
                '}';
    }
}
