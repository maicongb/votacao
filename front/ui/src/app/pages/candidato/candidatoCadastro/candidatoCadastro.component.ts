import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Candidato } from 'src/models/candidato';
import { CandidatoService } from 'src/services/candidato/candidato.service';
import { Constants } from 'src/util/constants';

@Component({
  selector: 'app-candidatoCadastro',
  templateUrl: './candidatoCadastro.component.html',
  styleUrls: ['./candidatoCadastro.component.scss']
})
export class CandidatoCadastroComponent implements OnInit {
  private readonly ModoInsercao = 'post';
  private readonly ModoAtualizacao = 'put';

  public candidato!: any;
  public id = 0;
  public idParameter = '';
  public modo = 'post';
  public form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private candidatoService: CandidatoService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  get isAtualizacao(): boolean{
    return (this.modo === this.ModoAtualizacao);
  }

  get isInsercao(): boolean{
    return (this.modo === this.ModoInsercao);
  }

  get f(): any{
    return this.form.controls;
  }

  ngOnInit() {
    this.id = this.getIdParamenter();
    if(this.idParamIsValid()){
      this.modo = this.ModoAtualizacao;
      this.carregaCandidato(this.id);
    }else{
      this.modo = this.ModoInsercao;
      this.carregaFormInclusao();
    }
  }


  public getIdParamenter(): number{

    if (this.activatedRoute.snapshot.firstChild !== null ){
      return  +this.activatedRoute.snapshot.firstChild?.params.id;
    } else {
      return 0;
    }

  }

  public idParamIsValid(): boolean{
    return  (this.id !== null && this.id !== 0);
  }

  public getForm(): FormGroup{
    return this.fb.group({
      ativo: [1, [ Validators.required ]],
      nome  : ['', [ Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      email  : ['', [ Validators.required, Validators.email]],
      instagram  : ['', [ Validators.required, Validators.pattern(Constants.REGEX_INSTAGRAM)]],
      dtNascimento  : ['', [ Validators.required ]],
      apelido : ['', [ Validators.required ]] ,
      partido : ['', [ Validators.required ]] ,
      numero : ['', [ Validators.required ]] ,
      telefone : [''] ,
      localidade : ['', [ Validators.required ]] ,
      descricao : [''] ,
    });
  }

  public getCandidatoVazio(): Candidato{
    return {
      id: 0,
      ativo: 1,
      nome: '',
      email: '',
      instagram: '',
      dtNascimento: '',
      apelido: '',
      partido: '',
      numero: 0,
      telefone: '',
      localidade: '',
      descricao: '',
      enquetes: [],
    } as Candidato;
  }

  public carregaCandidato(id: number): void{
      this.candidatoService.getById(this.id).subscribe(
        {
          next: (candidato: Candidato) => {
            this.candidato = {... candidato},

            this.form = this.getForm();
            this.form.patchValue(this.candidato);
          },
          error: (erro: any) => {
            this.Mensagem('Erro ao recuperar candidato. \n' + erro.message);
            console.error(erro);
          },
          complete: () => {

          }
        }
      );
  }

  public carregaFormInclusao(): void{
    this.id = 0;
    this.candidato = this.getCandidatoVazio();
    this.form = this.getForm();
  }

  public Salvar(): void{
    if(this.form.valid){
      if( this.isInsercao ){
        this.Inserir();
      } else {
        this.Atualizar();
      }
    }

  }

  public Atualizar(): void{
    this.candidato = {id: this.id, ... this.form.value};
    this.candidatoService.put( this.candidato ).subscribe({
      next : () => {
        this.Mensagem('Candidato atualizado.');
      },
      error : (error: any) => {
        this.erroAtualizacao(error);
      },
      complete : () => {

      }
    });
  }

  public Inserir(): void{
    this.candidato = {id: this.id, ... this.form.value}
    this.candidatoService.post( this.candidato ).subscribe({
      next : () => {
        this.Mensagem('Candidato inserido.');
      },
      error : (error: any) => {
        this.erroAtualizacao(error);
      },
      complete : () => {

      }
    });
  }

  public goToCadastro(id: number ): void{
    this.router.navigate([id], {relativeTo: this.activatedRoute});
  }

  public erroAtualizacao(error : any): void{
    console.error(error);
    this.Mensagem('Erro na atualização de inscrito.\n' + error.message );
  }

  public Mensagem(texto: string): void{
    this.snackBar.open(texto, 'Fechar');
  }

}
