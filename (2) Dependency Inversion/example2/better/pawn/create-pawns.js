const { Pawn } = require("./pawn");

// Run: node create-pawns.js
// One expensive creation, seven cheap clones.
const start = Date.now();
const pawn1 = new Pawn(0, 1, "white");
const pawn2 = pawn1.clone({ x: 1 });
const pawn3 = pawn1.clone({ x: 2 });
const pawn4 = pawn1.clone({ x: 3 });
const pawn5 = pawn1.clone({ x: 4 });
const pawn6 = pawn1.clone({ x: 5 });
const pawn7 = pawn1.clone({ x: 6 });
const pawn8 = pawn1.clone({ x: 7 });

console.log(`8 pawns created in ~${Date.now() - start}ms (problem version: 8 setups, ~200ms)`);
