%{
	#include<stdio.h>
%}

%token VERB PUNCT NOUN PRONOUN ADVERB ADJ CONJ

%%
Compound:Simple CONJ Simple					{printf("Valid compound statement\n");}
		     | Compound CONJ Simple 		{printf("Valid compound statement\n");}
		     | Simple										{printf("Valid simple statement\n");}
;
Simple:Subject VERB PUNCT 						
	      | Subject VERB Object PUNCT
	      | Subject VERB
	      | Subject VERB Object
;
Subject:NOUN
	       | PRONOUN
;
Object:NOUN 
	     | ADVERB 
	     | ADJ NOUN
;	
%%

void main()
{
	extern FILE* yyin;
	yyin = fopen("input.txt","r");
	yyparse();
}	

int yyerror(char *s)
{
	printf("%s\n", s);
}
