package br.com.grupo27.techchallenge03.domain.valuesObjects;

import java.util.Objects;

public class ValidadorCPF {

  private String cpf;

  private ValidadorCPF() {
    this.cpf = "11111111111";
  }

  public ValidadorCPF(String cpf) {
      if (cpf == null || (!cpf.isEmpty() && (!cpf.matches("\\d{11}") || !validarDigitosVerificadores(cpf)))) {
          throw new IllegalArgumentException("CPF inválido");
      }
      this.cpf = cpf;
  }


  public String getValor() {
    return cpf;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ValidadorCPF cpf = (ValidadorCPF) o;
    return cpf.equals(cpf.cpf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpf);
  }

  @Override
    public String toString() {
        return cpf;
    }

  public static boolean validarDigitosVerificadores(String cpf) {
    // Verifica se o CPF não tem uma sequência de números iguais
    if (cpf.equals("00000000000")
        || cpf.equals("11111111111")
        || cpf.equals("22222222222")
        || cpf.equals("33333333333")
        || cpf.equals("44444444444")
        || cpf.equals("55555555555")
        || cpf.equals("66666666666")
        || cpf.equals("77777777777")
        || cpf.equals("88888888888")
        || cpf.equals("99999999999")
        || (cpf.length() != 11)) return (false);

    char dig10, dig11;
    int soma, i, r, num, peso;

    // Calcula o primeiro dígito verificador
    soma = 0;
    peso = 10;
    for (i = 0; i < 9; i++) {
      num = (int) (cpf.charAt(i) - 48);
      soma = soma + (num * peso);
      peso = peso - 1;
    }

    r = 11 - (soma % 11);
    if ((r == 10) || (r == 11)) dig10 = '0';
    else dig10 = (char) (r + 48);

    // Calcula o segundo dígito verificador
    soma = 0;
    peso = 11;
    for (i = 0; i < 10; i++) {
      num = (int) (cpf.charAt(i) - 48);
      soma = soma + (num * peso);
      peso = peso - 1;
    }

    r = 11 - (soma % 11);
    if ((r == 10) || (r == 11)) dig11 = '0';
    else dig11 = (char) (r + 48);

    // Verifica se os dígitos calculados conferem com os dígitos informados
    if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) return (true);
    else return (false);
  }
}
