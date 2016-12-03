{- REDMOND AILBHE 12313945 -}

module Lab02 where

thisIsLab02 = "This is Lab 2"

type SegmentId = String -- unique name for segment
type Distance = Integer  -- metres
type Time = Integer  -- secs

type Segment = ( SegmentId, Distance, Time )

-- handy functions to access Segment components
segmentId :: Segment -> SegmentId
segmentId ( s, _, _ ) = s
distance :: Segment -> Distance
distance ( _, d, _ ) = d
time :: Segment -> Time
time ( _, _, t ) = t

-- all tests for this exercise guarantee the following:
segmentInvariant :: Segment -> Bool
segmentInvariant (_, d, t) = d > 0 && t > 0
 
type SegPath = [Segment]

-- all tests for this exercise guarantee the following
-- (at the top-level)
pathInvariant :: SegPath -> Bool
pathInvariant [] = False -- no empty paths
pathInvariant p = all segmentInvariant p

type Speed = Double -- km/hr
type Pace  = Double -- mins/km

pathSpeed :: SegPath -> Speed -- assumes pathInvariant
pathSpeed p = 3.6 * (fromInteger $ pathDistance p) / (fromInteger $ pathTime p)
 
pathPace :: SegPath -> Pace -- assumes pathInvariant
pathPace p = 100 * (fromInteger $ pathTime p) / (6 * (fromInteger $ pathDistance p))

{- ============ Task 1 =============
  Implement pathDistance to compute the
  total distance of a path by adding up the
  distances of its segments
-}

pathDistance :: SegPath -> Distance
pathDistance [] = 0
pathDistance x = distance (head x) + pathDistance(tail x)

{- ============ Task 2 =============
  Implement pathTime to compute the
  total time of a path by adding up the
  times of its segments
-}

pathTime :: SegPath -> Time
pathTime [] = 0
pathTime x = time (head x) + pathTime(tail x)

{- ============ Task 3 =============
  Function getMeasured has two arguments

  rqdDist :: Distance
  p ::SegPath

  It returns a prefix of p whose distance 
  is either equal to rqdDist, or just over,
  where possible.
  If the distance of p is less than rqdDist
  it returns all of p
-}

getMeasured :: Distance   -- desired distance of sub-SegPath
            -> SegPath    -- path
            -> SegPath    -- shortest path prefix greater than or equal to
getMeasured rqdDist [] = []
getMeasured rqdDist p 
    | pathDistance q >= rqdDist = q
    | otherwise = p
	where q = getMeasured (rqdDist) (init p)

{- ============ Task 3 =============
  Function findFastest has two arguments

  rqdDist :: Distance
  p ::SegPath

  It looks for a sub-path of p of
  length rqdDist 
  (or slightly above, as returned by getMeasured)
  that has the fastest speed, as given by pathSpeed.

  It returns Nothing if the initial path p is too short,
  otherwise it returns Just (pf,sf)
  where pf is the fastest SeqPath, and sf is its speed.

  When two sub-paths have the same speed (unlikely)
  it returns the one that occurs first.
-}

findFastest :: Distance -- desired distance of sub-SegPath
            -> SegPath
            -> Maybe -- fail if path too short
                 ( SegPath   -- fastest sub-SegPath
                 , Speed ) -- fastest Speed
findFastest rqdDist [] = Nothing
findFastest rqdDist p
    | rqdDist > pathDistance b = Nothing
    | otherwise = Just (maxSpeed a)
  where a = getPaths rqdDist p
        b = getMeasured rqdDist p

getPaths :: Distance -> SegPath -> [(SegPath, Speed)]
getPaths _ [] = []
getPaths rqdDist (p:ps)
  | pathDistance (p:ps) < rqdDist = []
  | length (p:ps) > 0 = [tuple] ++ getPaths rqdDist ps
  | otherwise = []
  where tuple = (getMeasured rqdDist (p:ps), pathSpeed (getMeasured rqdDist (p:ps)))

maxSpeed :: [(SegPath, Speed)] -> (SegPath, Speed)
maxSpeed [] = error "maximum of empty list"
maxSpeed (x:xs) = maxTail x xs

maxTail :: (SegPath, Speed) -> [(SegPath, Speed)] -> (SegPath, Speed)
maxTail currentMax [] = currentMax
maxTail (m, n) (p:ps) = 
  if n < (snd p)
    then maxTail p ps
    else maxTail (m, n) ps
          

{- ==== DO NOT CHANGE ANYTHING BELOW THIS LINE ==== -}

_p3 = [ ("_1",2,10)
      , ("_2",2,5)
      , ("_3",2,7)
      ]

_s :: Integer -> Segment
_s n = ("_s"++show n,100+n*(-1)^n,10+5*n)
_p9@[_s0,_s1,_s2,_s3,_s4,_s5,_s6,_s7,_s8,_s9] = map _s [0..9]

_ss :: Integer -> Segment
_ss n = ("_ss"++show n,8+n `mod` 5,_run_profile n + n `mod` 3)

_run_profile n
 | n <= 10    =  6 - 4 * n `div` 10
 | n <= 15    =  2
 | n <= 20    =  3
 | otherwise  =  3 + n20 `div` 10
 where n20 = n - 20

_p1000 = map _ss [0..100]




