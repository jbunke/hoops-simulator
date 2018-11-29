# Hoops Simulator
*Hoops Simulator* simulates seasons of fantasy basketball. The league, conferences, divisions, format, teams, players, and stats are
all customisable. *HS* includes the possibility to play with current NBA teams and players, assigning player attribute stats by pulling
data directly from [Basketball-Reference.com](https://www.basketball-reference.com).

### 1. Implemented features

 - NBA player data is pulled from Basketball-Reference and stores locally
 - Regular and post-season simulation structure with seeding and bracketing
 - Position has impact on rating (eg. PG assessed more heavily on shooting and assists than C)

### 2. Planned features

 - Comprehensive command line control
 - Save/Load state
 - Modes
   + NBA season mode
   + Custom league
   + Exhibition game
 - Passage of Time:
   + Have players age and performance peak or dip as they approach and pass their prime, respectively
   + Have players retire based on age & athleticism
   + Steady influx of new fictional generated players from college that get drafted into league every season
 - Circumstances:
   + Have player performance saved as stats and use generated game stats to calculate player ability

### 3. Possible extension

 - Add interface and make season playable as a certain team. Make games with team playable and simulate all others.

## Disclaimer and Help
This project is still a work in progress and is not practically usable at this stage of development.

Source code is in `src/hoops_root/` and the start class is `Main.java`. However, this class's contents are only used for testing purposes at the moment.
