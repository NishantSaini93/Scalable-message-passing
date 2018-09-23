-module(exchange).
-import(lists,[append/2]). 
-import(lists,[nth/2]).
-import(calling,[start/1]).
-export([start/0]).
-import(lists,[merge/1]).
-export([printingmessages/1]).
-export([menudisplay/2,menudisplay/3]).
-export([pidproduction/2,pidproduction/3]).


printingmessages(Rt)->
	receive
	    {msgtomasteri,Tup}->
		Tup1=nth(1,Tup),
		Tup2=nth(2,Tup),
		Tup3=nth(3,Tup),
		Ds=nth(4,Tup),
		  io:fwrite("~w",[Tup1]),
		  io:fwrite(" received intro message from "),
		  io:fwrite("~w",[Tup2]),
		  io:fwrite(" [~w]~n",[Tup3]),
		  printingmessages(Ds);
		  
		{msgtomasterr,ATupr}->
		   ATupr1=nth(1,ATupr),
           ATupr2=nth(2,ATupr),
           ATupr3=nth(3,ATupr),
		   Ds=nth(4,ATupr),
		  io:fwrite("~w",[ATupr1]),
		  io:fwrite(" received reply message from "),
		  io:fwrite("~w",[ATupr2]),
		  io:fwrite(" [~w]~n",[ATupr3]),
		  printingmessages(Ds)
	after Rt->io:fwrite("Master has received no replies for 1.5 seconds,ending...~n"),
	unregister(exchange)
end.
		


pidproduction(L,List1)->pidproduction(L,List1,1).
pidproduction([],List1,Dc2)->List1,Dc2;
pidproduction([_|T],List1,Dc2)->
    ListX=List1,
	WholeM=maps:from_list(ListX),
	Nmapkeys=maps:keys(WholeM),
	Lengthofcaller=length(Nmapkeys),
	
	Tup=nth(Dc2,ListX),
	List2=tuple_to_list(Tup),
	Caller=nth(1,List2),
	Cl=nth(2,List2),
	Mlist=[Caller,Cl,Lengthofcaller],
		 IdofProcess=spawn(calling, start, [Mlist]),
        register(Caller, IdofProcess),
	
	pidproduction(T,List1,Dc2+1).




	
	
menudisplay(L,List1)->menudisplay(L,List1,1).
menudisplay([],List1,Dc2)->List1,Dc2;
menudisplay([_|T],List1,Dc2)->
	Tup=nth(Dc2,List1),
	List2=tuple_to_list(Tup),
	Caller=nth(1,List2),
	Cl=nth(2,List2),
    io:fwrite("~w: ",[Caller]),
	io:fwrite("~p~n",[Cl]),
	menudisplay(T,List1,Dc2+1).
	

start()->
    register(exchange,self()),
	T=file:consult("calls.txt"),
	List1=tuple_to_list(T),
	List2=nth(2,List1),
	io:fwrite("** Calls to be made **~n"),
	menudisplay(List2,List2),
	io:fwrite("~n"),
	pidproduction(List2,List2),
    printingmessages(3000).

	

	
	 