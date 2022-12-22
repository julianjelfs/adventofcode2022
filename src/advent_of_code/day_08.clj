(ns advent-of-code.day-08
  (:require [clojure.string :as str]))

(defn char->int [c]
  (Integer/parseInt (str c)))

(defn create_grid [input]
  (map #(map char->int %) (str/split-lines input)))

(defn dimensions [grid]
  [(count (first grid)) (count grid)])

(defn north [grid x y]
  (map #(nth % x) (take y grid)))

(defn south [grid x y]
  (map #(nth % x) (drop (inc y) grid)))

(defn east [grid x y]
  (let [row (nth grid y)]
    (drop (inc x) row)))

(defn west [grid x y]
  (let [row (nth grid y)]
    (take x row)))

(defn tree_height [grid x y]
  (nth (nth grid y) x))

(defn all_smaller [xs x]
  (every? #(< % x) xs))

(defn views_from [grid x y]
  [(north grid x y)
   (south grid x y)
   (east grid x y)
   (west grid x y)])

(defn tree_visible? [[n s e w] grid x y]
  (let [h (tree_height grid x y)]
    (or (all_smaller n h) (all_smaller s h) (all_smaller e h) (all_smaller w h))))

(defn find-index [pred lst]
  (first (first (filter #(pred (first %) (second %)) (map-indexed vector lst)))))

(defn viewing_distance [xs x]
  (cond
    (empty? xs) 0
    :else (let [idx (find-index (fn [i h] (>= h x)) xs)]
            (cond
              idx (inc idx)
              :else (count xs)))))

(defn scenic_score [[n s e w] grid x y]
  (let [h (tree_height grid x y)]
    (*
     (viewing_distance (reverse n) h)
     (viewing_distance s h)
     (viewing_distance e h)
     (viewing_distance (reverse w) h))))

(defn visible_trees [grid]
  (let [[x y] (dimensions grid)]
    (for [x' (range 0 x)
          y' (range 0 y)
          :let [views (views_from grid x' y')
                h (tree_height grid x' y')
                v (tree_visible? views grid x' y')
                s (scenic_score views grid x' y')
                c [x' y']]]
      [v c h s])))

(defn part-1
  "Day 08 Part 1"
  [input]
  (count (filter identity (map first (visible_trees (create_grid input))))))

(defn part-2
  "Day 08 Part 2"
  [input]
  (last (sort (map #(nth % 3) (visible_trees (create_grid input))))))
