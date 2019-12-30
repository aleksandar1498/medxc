export class Event{
  title:string;
  start:string;
  end:string;
  constructor(title:string,start:string,end:string=null){
    this.title = title;
    this.start = start;
    this.end = end;
  }
}
