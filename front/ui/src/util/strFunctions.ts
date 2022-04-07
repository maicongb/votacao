export class StrFunctions {

  public static Contem(texto: string, termo: string): boolean{
    return texto.toLocaleLowerCase().indexOf(termo.toLocaleLowerCase()) !== -1;
  }

}
