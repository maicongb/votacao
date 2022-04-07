import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Candidato } from 'src/models/candidato';
import { StrFunctions } from 'src/util/strFunctions';

@Component({
  selector: 'app-candidato',
  templateUrl: './candidato.component.html',
  styleUrls: ['./candidato.component.scss']
})
export class CandidatoComponent implements OnInit {
  private filtroListados = '';
  public candidatos: Candidato[] = [ ];
  public candidatosFiltrados: Candidato[] = [ ];


  constructor(
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router

  ) { }

  public get filtroLista(): string{
    return this.filtroListados;
  }

  public set filtroLista(value: string){
    this.filtroListados = value;
    this.candidatosFiltrados = this.filtroLista ? this.filtrarLista(this.filtroLista) : this.candidatos;
  }

  public filtrarLista(filtrarPor: string): Candidato[] {
    filtrarPor = filtrarPor.toLocaleLowerCase();
    return this.candidatos.filter(
      (candidato: any) => StrFunctions.Contem(candidato.nome, filtrarPor) ||
                          StrFunctions.Contem(candidato.email, filtrarPor)
    ) ;
  }

  ngOnInit() {

  }

  public Mensagem(texto: string): void{
    this.snackBar.open(texto, 'Fechar');
  }

  public AbrirCadastro(id: number): void{
    this.router.navigate([`candidatoCadastro/${id}`]);
  }
}
