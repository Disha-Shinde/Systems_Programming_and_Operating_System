%{
	#include "y.tab.h"
%}

%%
"int"					{return DTYPE;}
[a-zA-Z0-9_]+			{return ID;}
";"						{return SEMI;}
","						{return COMMA;}
%%

int yywrap()
{
	return 1;
}
