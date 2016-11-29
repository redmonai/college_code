{-# LANGUAGE StandaloneDeriving #-}

module Main where

import Test.HUnit
import Test.Framework as TF (defaultMain, testGroup, Test)
import Test.Framework.Providers.HUnit (testCase)
import Test.Framework.Providers.QuickCheck2 (testProperty)

import Lab02

{- HUnit Tests -}


test_dist_null  = pathDistance [] @?= 0

test_time_null  = pathTime [] @?= 0

test_meas_null  = getMeasured 100 [] @?= []
test_meas_1_p3 = getMeasured 1 _p3 @?= [("_1",2,10)]
test_meas_2_p3 = getMeasured 2 _p3 @?= [("_1",2,10)]
test_meas_3_p3 = getMeasured 3 _p3 @?= [("_1",2,10),("_2",2,5)]
test_p9_measures
  = map f [1..10] 
    @?=
    [ [("_s0",100,10)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20),("_s3",97,25),("_s4",104,30)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20),("_s3",97,25),("_s4",104,30)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20),("_s3",97,25),("_s4",104,30)
      ,("_s5",95,35),("_s6",106,40)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20),("_s3",97,25),("_s4",104,30)
      ,("_s5",95,35),("_s6",106,40)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20),("_s3",97,25),("_s4",104,30)
      ,("_s5",95,35),("_s6",106,40),("_s7",93,45),("_s8",108,50)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20),("_s3",97,25),("_s4",104,30)
      ,("_s5",95,35),("_s6",106,40),("_s7",93,45),("_s8",108,50)]
    , [("_s0",100,10),("_s1",99,15),("_s2",102,20),("_s3",97,25),("_s4",104,30)
      ,("_s5",95,35),("_s6",106,40),("_s7",93,45),("_s8",108,50),("_s9",91,55)]]
  where f n = getMeasured (100*n) _p9

test_fast_null  = findFastest 100 [] @?= Nothing
test_fast_1_p3  = findFastest 1 _p3  @?= Just ([("_2",2,5)],1.44)
test_fast_2_p3  = findFastest 2 _p3  @?= Just ([("_2",2,5)],1.44)
test_fast_3_p3  = findFastest 3 _p3  @?= Just ([("_2",2,5),("_3",2,7)],1.2)
test_fast_4_p3  = findFastest 4 _p3  @?= Just ([("_2",2,5),("_3",2,7)],1.2)
test_fast_5_p3  = findFastest 5 _p3  @?= Just ([("_1",2,10),("_2",2,5),("_3",2,7)],0.9818181818181819)
test_fast_6_p3  = findFastest 6 _p3  @?= Just ([("_1",2,10),("_2",2,5),("_3",2,7)],0.9818181818181819)
test_fast_7_p3  = findFastest 7 _p3  @?= Nothing
test_p1000_fastest
  = map g [0..5]
    @?=
    [ Just  ([("_ss9",12,3),("_ss10",8,3),("_ss11",9,4),("_ss12",10,2),("_ss13",11,3)
             ,("_ss14",12,4),("_ss15",8,2),("_ss16",9,4),("_ss17",10,5),("_ss18",11,3)]
            ,10.909090909090908)
    , Just  ([("_ss5",8,6),("_ss6",9,4),("_ss7",10,5),("_ss8",11,5),("_ss9",12,3)
             ,("_ss10",8,3),("_ss11",9,4),("_ss12",10,2),("_ss13",11,3),("_ss14",12,4)
             ,("_ss15",8,2),("_ss16",9,4),("_ss17",10,5),("_ss18",11,3),("_ss19",12,4)
             ,("_ss20",8,5),("_ss21",9,3),("_ss22",10,4),("_ss23",11,5),("_ss24",12,3)
             ,("_ss25",8,4),("_ss26",9,5),("_ss27",10,3),("_ss28",11,4),("_ss29",12,5)
             ,("_ss30",8,4),("_ss31",9,5),("_ss32",10,6),("_ss33",11,4),("_ss34",12,5)]
            ,8.852459016393443)
    , Just  ([("_ss0",8,6),("_ss1",9,7),("_ss2",10,8),("_ss3",11,5),("_ss4",12,6)
             ,("_ss5",8,6),("_ss6",9,4),("_ss7",10,5),("_ss8",11,5),("_ss9",12,3)
             ,("_ss10",8,3),("_ss11",9,4),("_ss12",10,2),("_ss13",11,3),("_ss14",12,4)
             ,("_ss15",8,2),("_ss16",9,4),("_ss17",10,5),("_ss18",11,3),("_ss19",12,4)
             ,("_ss20",8,5),("_ss21",9,3),("_ss22",10,4),("_ss23",11,5),("_ss24",12,3)
             ,("_ss25",8,4),("_ss26",9,5),("_ss27",10,3),("_ss28",11,4),("_ss29",12,5)
             ,("_ss30",8,4),("_ss31",9,5),("_ss32",10,6),("_ss33",11,4),("_ss34",12,5)
             ,("_ss35",8,6),("_ss36",9,4),("_ss37",10,5),("_ss38",11,6),("_ss39",12,4)
             ,("_ss40",8,6),("_ss41",9,7),("_ss42",10,5),("_ss43",11,6),("_ss44",12,7)
             ,("_ss45",8,5),("_ss46",9,6),("_ss47",10,7),("_ss48",11,5),("_ss49",12,6)]
            ,7.531380753138075)
    , Just  ([("_ss0",8,6),("_ss1",9,7),("_ss2",10,8),("_ss3",11,5),("_ss4",12,6)
             ,("_ss5",8,6),("_ss6",9,4),("_ss7",10,5),("_ss8",11,5),("_ss9",12,3)
             ,("_ss10",8,3),("_ss11",9,4),("_ss12",10,2),("_ss13",11,3),("_ss14",12,4)
             ,("_ss15",8,2),("_ss16",9,4),("_ss17",10,5),("_ss18",11,3),("_ss19",12,4)
             ,("_ss20",8,5),("_ss21",9,3),("_ss22",10,4),("_ss23",11,5),("_ss24",12,3)
             ,("_ss25",8,4),("_ss26",9,5),("_ss27",10,3),("_ss28",11,4),("_ss29",12,5)
             ,("_ss30",8,4),("_ss31",9,5),("_ss32",10,6),("_ss33",11,4),("_ss34",12,5)
             ,("_ss35",8,6),("_ss36",9,4),("_ss37",10,5),("_ss38",11,6),("_ss39",12,4)
             ,("_ss40",8,6),("_ss41",9,7),("_ss42",10,5),("_ss43",11,6),("_ss44",12,7)
             ,("_ss45",8,5),("_ss46",9,6),("_ss47",10,7),("_ss48",11,5),("_ss49",12,6)
             ,("_ss50",8,8),("_ss51",9,6),("_ss52",10,7),("_ss53",11,8),("_ss54",12,6)
             ,("_ss55",8,7),("_ss56",9,8),("_ss57",10,6),("_ss58",11,7),("_ss59",12,8)
             ,("_ss60",8,7),("_ss61",9,8),("_ss62",10,9),("_ss63",11,7),("_ss64",12,8)
             ,("_ss65",8,9),("_ss66",9,7),("_ss67",10,8),("_ss68",11,9),("_ss69",12,7)]
            ,6.4781491002570695)
    , Just  ([("_ss0",8,6),("_ss1",9,7),("_ss2",10,8),("_ss3",11,5),("_ss4",12,6)
             ,("_ss5",8,6),("_ss6",9,4),("_ss7",10,5),("_ss8",11,5),("_ss9",12,3)
             ,("_ss10",8,3),("_ss11",9,4),("_ss12",10,2),("_ss13",11,3),("_ss14",12,4)
             ,("_ss15",8,2),("_ss16",9,4),("_ss17",10,5),("_ss18",11,3),("_ss19",12,4)
             ,("_ss20",8,5),("_ss21",9,3),("_ss22",10,4),("_ss23",11,5),("_ss24",12,3)
             ,("_ss25",8,4),("_ss26",9,5),("_ss27",10,3),("_ss28",11,4),("_ss29",12,5)
             ,("_ss30",8,4),("_ss31",9,5),("_ss32",10,6),("_ss33",11,4),("_ss34",12,5)
             ,("_ss35",8,6),("_ss36",9,4),("_ss37",10,5),("_ss38",11,6),("_ss39",12,4)
             ,("_ss40",8,6),("_ss41",9,7),("_ss42",10,5),("_ss43",11,6),("_ss44",12,7)
             ,("_ss45",8,5),("_ss46",9,6),("_ss47",10,7),("_ss48",11,5),("_ss49",12,6)
             ,("_ss50",8,8),("_ss51",9,6),("_ss52",10,7),("_ss53",11,8),("_ss54",12,6)
             ,("_ss55",8,7),("_ss56",9,8),("_ss57",10,6),("_ss58",11,7),("_ss59",12,8)
             ,("_ss60",8,7),("_ss61",9,8),("_ss62",10,9),("_ss63",11,7),("_ss64",12,8)
             ,("_ss65",8,9),("_ss66",9,7),("_ss67",10,8),("_ss68",11,9),("_ss69",12,7)
             ,("_ss70",8,9),("_ss71",9,10),("_ss72",10,8),("_ss73",11,9),("_ss74",12,10)
             ,("_ss75",8,8),("_ss76",9,9),("_ss77",10,10),("_ss78",11,8),("_ss79",12,9)
             ,("_ss80",8,11),("_ss81",9,9),("_ss82",10,10),("_ss83",11,11),("_ss84",12,9)
             ,("_ss85",8,10),("_ss86",9,11),("_ss87",10,9),("_ss88",11,10),("_ss89",12,11)]
            ,5.586206896551724)
    , Nothing ]
  where
    g n = findFastest (100+200*n) _p1000



{- QuickCheck Tests -}

path_dist_single :: Segment -> Bool
path_dist_single s@(_,d,_)
  =  pathDistance [s] == d

path_dist_cat :: SegPath -> SegPath -> Bool
path_dist_cat p1 p2
  = pathDistance p1 + pathDistance p2 == pathDistance (p1 ++ p2)
  
path_time_single :: Segment -> Bool
path_time_single s@(_,_,t)
  =  pathTime [s] == t

path_time_cat :: SegPath -> SegPath -> Bool
path_time_cat p1 p2
  = pathTime p1 + pathTime p2 == pathTime (p1 ++ p2)

  
{- Test Main -}

main = defaultMain tests

tests :: [TF.Test]
tests 
  = [ testGroup "\nTotal Marks Available: 20\nBig Tests (2 marks)" 
      [ testCase "_p1000 Fastest {1 mark}" $ test_p1000_fastest
      , testCase "_p9 Measures {1 mark}" $ test_p9_measures
      ]
    , testGroup "pathDistance (3 marks)" 
      [ testCase "pathDistance [] {1 mark}" 
                $ test_dist_null
      , testProperty "pathDistance [s] {1 mark}" path_dist_single
      , testProperty "pathDistance cat {1 mark}" path_dist_cat
      ]
    , testGroup "pathTime (3 marks)" 
      [ testCase "pathTime [] {1 mark}" 
                $ test_time_null
      , testProperty "pathTime [s] {1 mark}" path_time_single
      , testProperty "pathTime cat {1 mark}" path_time_cat
      ]
    , testGroup "getMeasured (5 marks)" 
      [ testCase "getMeasured [] {1 mark}" $ test_meas_null
      , testCase "getMeasured 1 _p3 {1 mark}" $ test_meas_1_p3
      , testCase "getMeasured 2 _p3 {1 mark}" $ test_meas_2_p3
      , testCase "getMeasured 3 _p3 {1 mark}" $ test_meas_3_p3
      ]
    , testGroup "findFastest (9 marks)" 
      [ testCase "findFastest [] {1 mark}" $ test_fast_null
      , testCase "getFastest 1 _p3 {1 mark}" $ test_fast_1_p3
      , testCase "getFastest 2 _p3 {1 mark}" $ test_fast_2_p3
      , testCase "getFastest 3 _p3 {1 mark}" $ test_fast_3_p3
      , testCase "getFastest 4 _p3 {1 mark}" $ test_fast_4_p3
      , testCase "getFastest 5 _p3 {1 mark}" $ test_fast_5_p3
      , testCase "getFastest 6 _p3 {1 mark}" $ test_fast_6_p3
      , testCase "getFastest 7 _p3 {1 mark}" $ test_fast_7_p3
      ]
    ]
