# Scalable-message-passing
This project demonstrates the concurrent message passing.

Used programming languages:Erlang and Java

This will give you an idea how a thought process will be different for imperative and functional language.

The same program is coded in both Java and Erlang.

It implements a communication network for a group of friends. All friends will actually do, is send a contact message to one or more people in the group, and then wait for a confirmation reply from that person.

Input:
Text files:

calls.txt:Calls to be made by friends.

Example:
{john, [jill,joe,bob]}.

{jill, [bob,joe,bob]}.

{sue, [jill,jill,jill,bob,jill]}.

{bob, [john]}.

{joe, [sue]}.

How to run:

You will need java and erlang environments.

Procedure to run java code:

1.Locate java file and make sure you have calls.txt in the same directory.

2.Open cmd and locate java file compile it with javac command.eg:javac exchange.java

3.Run it.eg:java exchange

Procedure to run Erlang code:

1.Locate Erlang file in cmd.

2.Compile both files:

-erlc exchange.erl

-erlc calling.erl

3.Run by using command:erl -noshell -s exchange -s init stop





