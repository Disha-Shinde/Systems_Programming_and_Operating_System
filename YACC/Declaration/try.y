%{
	#include<stdio.h>
%}

%token DTYPE ID SEMI COMMA

%%
DECL:DTYPE VARLIST SEMI				{printf("Valid");}
;
VARLIST:ID |
		VARLIST COMMA ID
;
%%

void main()
{
	extern FILE *yyin;
	yyin = fopen("input.txt", "r");
	yyparse();
}

int yyerror(char *s)
{
	return 1;
}
