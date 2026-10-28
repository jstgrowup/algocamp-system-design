const SETUP_MS = 25; // pretend loading the rules takes a while

// The expensive part. Runs once for the base pawn, never for clones.
function loadMovementRules() {
  console.log("  loading movement rules...");
  const end = Date.now() + SETUP_MS;
  while (Date.now() < end) {} // busy wait, stands in for real loading work
  return {
    forwardOnly: true,
    firstMoveTwoSquares: true,
    capturesDiagonally: true,
  };
}

module.exports = { loadMovementRules };
