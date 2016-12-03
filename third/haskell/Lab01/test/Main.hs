{-# LANGUAGE StandaloneDeriving #-}

module Main where

import Test.HUnit
import Test.Framework as TF (defaultMain, testGroup, Test)
import Test.Framework.Providers.HUnit (testCase)
import Test.Framework.Providers.QuickCheck2 (testProperty)

import Lab01

{- HUnit Tests -}

test_main_untampered =  thisIsLab01 @?= "This is Lab 1"

test_distance c1 c2 d  = distance c1 c2 @?= d

test_length p l  =  pathlength p @?= l

{- QuickCheck Tests -}

t_sqr d = d * d

prop_sqr :: Double -> Bool
prop_sqr d 
  = t_sqr d == sqr d
  
  
{- Test Main -}

main = defaultMain tests

tests :: [TF.Test]
tests 
 = [ testGroup "Lab01 Tamper-Free" 
     [
       testCase "Tamper Free ! [0 marks]" test_main_untampered
     ]
   , testGroup "Squaring" 
     [
       testProperty "Square [4 marks]" prop_sqr
     ]
   , testGroup "Distance" 
     [
       testCase "Distance _c01 _c02 [1 mark]" 
                $ test_distance _c01 _c02 1.0
     , testCase "Distance _c02 _c01 [1 mark]" 
                $ test_distance _c02 _c01 1.0
     , testCase "Distance _c06 _c03 [1 mark]" 
                $ test_distance _c06 _c03 2.23606797749979
     , testCase "Distance _c03 _c06 [1 mark]" 
                $ test_distance _c03 _c06 2.23606797749979
     , testCase "Distance _c01 _c10 [1 mark]" 
                $ test_distance _c01 _c10 4.242640687119285
     , testCase "Distance _c10 _c01 [1 mark]" 
                $ test_distance _c10 _c01 4.242640687119285
     ]
   , testGroup "Path Length" 
     [
       testCase "_path0 [2 mark]" 
                $ test_length _path0 0.0
     , testCase "_path1 [2 mark]" 
                $ test_length _path1 0.0
     , testCase "_path2 [2 mark]" 
                $ test_length _path2 2.0
     , testCase "_path3 [2 mark]" 
                $ test_length _path3 6.242640687119285
     , testCase "_path4 [1 mark]" 
                $ test_length _path4 9.242640687119284
     , testCase "_path5 [1 mark]" 
                $ test_length _path5 12.404918347287664
     ]
   ]
