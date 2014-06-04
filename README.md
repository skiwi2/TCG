A trading card game featuring a simple AI, initially designed for codereview.stackexchange.com Coding Challenge #2.

[![Build Status](https://travis-ci.org/skiwi2/TCG.svg?branch=master)](https://travis-ci.org/skiwi2/TCG?branch=master)  
[![Coverage Status](https://coveralls.io/repos/skiwi2/TCG/badge.png?branch=master)](https://coveralls.io/r/skiwi2/TCG?branch=master)

Known issues:
- You cannot attack monsters, because you cannot change a card to offensive mode.
- The game never ends.
- Only the following parts of the GUI are updated automatically* as events happen: Hand.
  * Manual update still happens when you end your turn.