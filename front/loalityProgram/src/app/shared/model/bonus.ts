import {User} from "./user";

export class Bonus{
  id: number;
  value: number;
  dateOfStart: string;
  dateOfEnd:string;
  bonusType: string;
  user: User;
}
