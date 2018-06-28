# Concordion Embed Extension

[This Concordion extension](https://github.com/concordion/concordion-embed-extension) adds an embed command that embeds HTML in the Concordion output. 
It is similar to the echo command, except that it does not escape HTML text.

# [Embed Formatted Data](-)
Given an output that embeds HTML
Then the HTML text is not escaped [ ](- "ext:embed=embedExample()")
