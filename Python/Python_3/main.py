alphabet = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"]


def shift_letter(let, w):
    try:
        if alphabet.index(let) or let == "a":
            index = alphabet.index(let)
            if index + len(w) > len(alphabet) - 1:
                index = len(word) - (len(alphabet) - index)
            else:
                index = index + len(w)

            new_letter = alphabet[index]
            return new_letter

    except ValueError:
        new_letter = let
        return new_letter


file = open("Text.txt", 'r', encoding="utf-8")
text = file.readline().split()
new_text = []
for word in text:
    new_word = ''
    for letter in word:
        if letter.isupper():
            letter = shift_letter(letter.lower(), word)
            new_word += letter.upper()
        elif letter.islower():
            new_word += shift_letter(letter, word)
        else:
            new_word += letter

    new_text.append(new_word)
file.close()

file = open("Text.txt", 'w', encoding="utf-8")
file.write(" ".join(new_text))
file.close()

