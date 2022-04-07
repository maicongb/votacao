import { Candidato } from './candidato';
import { Enquete } from './enquete';
export interface Votante {
  id: number;
  nome: string;
  email: string;
  cpf: string;
  telefone: string;
  enquete: Enquete;
  candidato: Candidato;
}
