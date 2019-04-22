%{
	#include "y.tab.h"
%}

%%
"."|"!"|"?"												{return PUNCT;}
"and"|"but"|"therefore"|"or"							{return CONJ;}
"He"|"She"|"he"|"she"|"I"|"We"|"They"|"we"|"they"		{return PRONOUN;}
"reads"|"dancing"|"plays"|"is"							{return VERB;}
"beautiful"|"good"										{return ADJ;}
"slowly"|"nicely"										{return ADVERB;}
[a-zA-Z]+												{return NOUN;}
%%

int yywrap()
{
	return 1;
}
