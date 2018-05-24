import {User} from "./user";

export class Bonus{
  id: number;
  value: number;
  dateOfStart: number;
  dateOfEnd:number;
  bonusType: string;
  user: User;
}
