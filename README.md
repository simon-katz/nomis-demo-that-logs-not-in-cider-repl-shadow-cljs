# A minimal shadow-cljs example

## Useful Commands

`./_scripts/clean-fully-dev.sh ; ./_scripts/cljs-dev-auto.sh`

Also, see the other scripts in the `_scripts` directory.


## REPL-Based Development

Run this at the command line:

- `./_scripts/clean-fully-dev.sh ; ./_scripts/cljs-dev-auto.sh`

- Wait for the "Build completed" message.

Then:

- In CIDER, do: `M-x cider-connect-clj&cljs`. (What about non-CIDER?)
- In the CLJ REPL, run `(reset)`.
- Start/refresh a browser pointing at `http://localhost:3002/index.html`.
