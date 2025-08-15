from textblob import TextBlob
from language_tool_python import LanguageTool
from py4j.java_gateway import JavaGateway

class SpellCheckerModule:
    def __init__(self):
        self.grammar_check = LanguageTool('en-US')

    def correct_spell(self, text):
   
        words = text.split()
        corrected_words = []
        for word in words:
            corrected_word = str(TextBlob(word).correct())
            corrected_words.append(corrected_word)
        return " ".join(corrected_words)

    def correct_grammar(self, text):
        
        matches = self.grammar_check.check(text)

        found_mistakes = []
        for match in matches:
            mistake_text = text[match.offset:match.offset + match.errorLength]
            suggestions = match.replacements
            found_mistakes.append({
                'mistake': mistake_text,
                'suggestions': suggestions,
                'message': match.message,
                'rule': match.ruleId
            })

        found_mistakes_count = len(found_mistakes)
        return found_mistakes, found_mistakes_count

if __name__ == "__main__":
    gateway = JavaGateway()
    app = gateway.entry_point
    obj = SpellCheckerModule()

    mystr = ""
    mystr = app.setString()

    print("Corrected Spelling (Word by Word):")
    corrected_text = obj.correct_spell(mystr)
    print(corrected_text)

    print("\nGrammar and Spelling Issues:")
    issues, count = obj.correct_grammar(mystr)
    for issue in issues:
        print(f"- Mistake: '{issue['mistake']}'")
        print(f"  Suggestions: {issue['suggestions']}")
        print(f"  Rule: {issue['rule']}")
        print(f"  Message: {issue['message']}")
        print()

    text_to_pass_list = [issue['mistake'] for issue in issues if 'mistake' in issue]

    for mistake in text_to_pass_list:
        result = app.getRes(mistake)
    app.showRes()
    
    app.CorrectedString(corrected_text)
    app.ResultChanged(True)
    