% pterm(null). 
% pterm(f0(X)) :- pterm(X). 
% pterm(f1(X)) :- pterm(X).

% Problem 1: incr(P1, P2). P2 is the successor of P1.
incr(null, f1(null)).
incr(f0(X), f1(X)).
incr(f1(X), f0(Y)) :- incr(X, Y).

% Problem 2: legal(P). Lists all legal pterms.
legal(f0(null)).
legal(X) :- legal(Y) , incr(Y, X).

incrR(X, Y) :- legal(X) , incr(X, Y).

% Problem 3: add(P1, P2, P3). P3 is P1 plus P2.
add(X, f0(null), X).
add(f0(null), Y, Y).
add(X, Y, Z) :- 
	incr(Y, B),			% incr Y in result B
	incr(A, X),			% this will decr X
	add(A, B, Z).		% recursively continue addition on result B

% Problem 4: mult(P1, P2, P3). P3 is P1 times P2.
mult(X, Y, Z) :- mult(X, Y, f0(null), Z).
mult(X, f0(null), Sum, Sum).
mult(f0(null), Y, Sum, Sum).
mult(X, Y, Sum, Z) :-
	%multiplication is adding X to itself Y times
	incr(A, Y),			% decr Y
	add(X, Sum, B),		% add X to current Sum
	mult(X, A, B, Z).
	
% Problem 5: revers(P, RevP). Takes a pterm and reverses it.
revers(P, RevP) :- revers(P, null, RevP).
revers(null, RevP, RevP).
revers(f0(P), X, RevP) :- revers(P, f0(X), RevP).
revers(f1(P), X, RevP) :- revers(P, f1(X), RevP).

% Problem 6: normalize(P, Pn). Returns a legal pterm Pn that is P less its leading zeroes
%normalize(null, f0(null)).
normalize(P, Pn) :-
	revers(P, X),
	removeLeadingZero(X, Y),
	revers(Y, Pn).

removeLeadingZero(null, null).
removeLeadingZero(f1(X), f1(X)).
removeLeadingZero(f0(X), Y) :- removeLeadingZero(X, Y).

% test add inputting numbers N1 and N2
testAdd(N1, N2, T1, T2, Sum, SumT) :- numb2pterm(N1, T1), numb2pterm(N2, T2), 
						add(T1, T2, SumT), pterm2numb(SumT, Sum).

%test mult inputting numbers N1 and N2
testMult(N1,N2,T1,T2,N1N2,T1T2) :- numb2pterm(N1,T1), numb2pterm(N2,T2),
						mult(T1,T2,T1T2), pterm2numb(T1T2,N1N2).

% test revers inputting list L
testRev(L,Lr,T,Tr) :- ptermlist(T,L), revers(T,Tr), ptermlist(Tr,Lr).

% test normalize inputting list L
testNorm(L,T,Tn,Ln) :- ptermlist(T,L), normalize(T,Tn), ptermlist(Tn,Ln).

% make a pterm T from a number N numb2term(+N,?T)
numb2pterm(0,f0(null)).
numb2pterm(N,T) :- N>0, M is N-1, numb2pterm(M,Temp), incr(Temp,T).

% make a number N from a pterm T pterm2numb(+T,?N)
pterm2numb(null,0).
pterm2numb(f0(X),N) :- pterm2numb(X,M), N is 2*M.
pterm2numb(f1(X),N) :- pterm2numb(X,M), N is 2*M +1.

% reversible ptermlist(T,L)
ptermlist(null,[]).
ptermlist(f0(X),[0|L]) :- ptermlist(X,L).
ptermlist(f1(X),[1|L]) :- ptermlist(X,L).