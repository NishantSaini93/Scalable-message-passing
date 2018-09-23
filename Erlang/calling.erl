-module(calling).
-import(lists,[nth/2]).
-export([start/1]).
-export([callingperson/2,callingperson/3]).
-export([passingto/2]).
-import(exchange,[printingmessages/1]).


passingto(XParaname,Rs)->
 receive
    
        {messagetocallee,Clist}->
		                 IdC=nth(1,Clist),
						 NC=nth(2,Clist),
						 Ds=nth(3,Clist),
						{A1,A2,A3} = now(),
                         random:seed(A1, A2, A3),
						 TimeDis=A3,
						 Tun=[NC,XParaname,TimeDis,Ds],
						 whereis(exchange)!{msgtomasteri,Tun},
						 Sleeptimer2=rand:uniform(100),
						 timer:sleep(Sleeptimer2),
						 Tun1=[XParaname,NC,TimeDis,Ds],
						 IdC ! {replyofmessege,Tun1},
						passingto(XParaname,Ds);
         
		 {replyofmessege,Rlist}->
		                CN=nth(1,Rlist),
						NC=nth(2,Rlist),
						Ds=nth(4,Rlist),
						TimeDis1=nth(3,Rlist),
						Yup=[CN,NC,TimeDis1,Ds],
		                whereis(exchange)!{msgtomasterr,Yup},
						passingto(XParaname,Ds)
				after Rs-> io:fwrite("Process ~p has received no calls for 1 second,ending... ~n",[XParaname])
	end.

	
	

start(Mlist)->
  Mcal=nth(1,Mlist),
  Scal=nth(2,Mlist),
  Lcal=nth(3,Mlist),
  R1list=[whereis(Mcal),Scal],
  callingperson(Scal,R1list),
  Rs=Lcal*4000,
  passingto(Mcal,Rs).
 

callingperson(L,R1list)->callingperson(L,R1list,1).
callingperson([],R1list,Dc1)->R1list,Dc1;
callingperson([_|T],R1list,Dc1)->
  Gs=nth(1,R1list),
  L1=nth(2,R1list),
  Jiscall=nth(Dc1,L1),
  IdtoCall=whereis(Jiscall), 
  Ts=length(R1list),
Ds=Ts*2000,
Sleeptimer=rand:uniform(100),
timer:sleep(Sleeptimer),
Clist=[IdtoCall,Jiscall,Ds],
Gs ! {messagetocallee,Clist},
callingperson(T,R1list,Dc1+1). 	

