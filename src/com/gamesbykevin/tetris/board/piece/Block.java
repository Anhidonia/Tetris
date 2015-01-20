package com.gamesbykevin.tetris.board.piece;

import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.framework.resources.Disposable;
import com.gamesbykevin.tetris.menu.CustomMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * This is a Tetris block.<br> 
 * Multiple blocks form a Tetris piece
 * @author GOD
 */
public final class Block extends Cell implements Disposable
{
    //the dimensions of each block
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    //the dimensions of each block
    public static final int ISOMETRIC_WIDTH = 30;
    public static final int ISOMETRIC_HEIGHT = 30;
    
    //the color of the block
    private Color color;
    
    //all blocks that are part of the same piece will have the same id
    private long id;
    
    //these polygon objects are for the isometric blocks
    private Polygon topSide;
    private Polygon frontSide;
    private Polygon rightSide;
    
    /**
     * Create new block
     * @param col Column
     * @param row Row
     * @param color Color of block
     * @param id The id, all blocks that are part of the same piece will have the same id
     */
    protected Block(final int col, final int row, final Color color, final long id)
    {
        //call to parent constructor
        super(col, row);
        
        //set the id
        setId(id);
        
        //assign the color of the block
        setColor(color);
    }
    
    /**
     * Get the block id.<br>
     * All blocks that are part of the same piece will have the same id.
     * @return The id of the piece this block belongs to.
     */
    public long getId()
    {
        return this.id;
    }
    
    private void setId(final long id)
    {
        this.id = id;
    }
    
    public void setColor(final Color color)
    {
        this.color = color;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * Assign polygon coordinates for isometric rendering
     * @param x x-coordinate
     * @param y y-coordinate
     * @param renderIndex the type of rendering we are doing
     */
    private void assignCoordinates(final int x, final int y, final int renderIndex)
    {
        if (frontSide == null)
            frontSide = new Polygon();
        if (rightSide == null)
            rightSide = new Polygon();
        if (topSide == null)
            topSide = new Polygon();
        
        switch (renderIndex)
        {
            case CustomMenu.RENDER_ISOMETRIC_1:
                frontSide.reset();
                frontSide.addPoint(x, y);
                frontSide.addPoint(x + (WIDTH/2), y + (HEIGHT/2));
                frontSide.addPoint(x + (WIDTH/2), y);
                frontSide.addPoint(x, y - (HEIGHT/2));

                rightSide.reset();
                rightSide.addPoint(x + (WIDTH/2), y + (HEIGHT/2));
                rightSide.addPoint(x + WIDTH, y);
                rightSide.addPoint(x + WIDTH, y - (HEIGHT / 2));
                rightSide.addPoint(x + (WIDTH/2), y);

                topSide.reset();
                topSide.addPoint(x, y - (HEIGHT/2));
                topSide.addPoint(x + (WIDTH/2), y);
                topSide.addPoint(x + WIDTH, y - (HEIGHT/2));
                topSide.addPoint(x + (WIDTH/2), y - HEIGHT);
                break;
                
            case CustomMenu.RENDER_ISOMETRIC_2:
                frontSide.reset();
                frontSide.addPoint(x, y);
                frontSide.addPoint(x + (ISOMETRIC_WIDTH/2), y + (ISOMETRIC_HEIGHT/2));
                frontSide.addPoint(x + (ISOMETRIC_WIDTH/2), y);
                frontSide.addPoint(x, y - (ISOMETRIC_HEIGHT/2));

                rightSide.reset();
                rightSide.addPoint(x + (ISOMETRIC_WIDTH/2), y + (ISOMETRIC_HEIGHT/2));
                rightSide.addPoint(x + ISOMETRIC_WIDTH, y);
                rightSide.addPoint(x + ISOMETRIC_WIDTH, y - (ISOMETRIC_HEIGHT / 2));
                rightSide.addPoint(x + (ISOMETRIC_WIDTH/2), y);

                topSide.reset();
                topSide.addPoint(x, y - (ISOMETRIC_HEIGHT/2));
                topSide.addPoint(x + (ISOMETRIC_WIDTH/2), y);
                topSide.addPoint(x + ISOMETRIC_WIDTH, y - (ISOMETRIC_HEIGHT/2));
                topSide.addPoint(x + (ISOMETRIC_WIDTH/2), y - ISOMETRIC_HEIGHT);
                break;
                
            case CustomMenu.RENDER_2D:
                frontSide.reset();
                frontSide.addPoint(x, y);
                frontSide.addPoint(x + WIDTH, y);
                frontSide.addPoint(x + WIDTH, y + HEIGHT);
                frontSide.addPoint(x, y + HEIGHT);
                break;
        }
    }
    
    @Override
    public void dispose()
    {
        this.color = null;
    }
    
    /**
     * Calculate the y-coordinate for isometric rendering
     * @param piece The piece containing the column, row location
     * @return y-coordinate
     */
    public static int getIsometric1Y(final Piece piece)
    {
        return getIsometric1Y(piece.getCol(), piece.getRow());
    }
    
    /**
     * Calculate the y-coordinate for isometric rendering
     * @param block The block containing the column, row location
     * @return y-coordinate
     */
    public static int getIsometric1Y(final Block block)
    {
        return getIsometric1Y(block.getCol(), block.getRow());
    }
    
    /**
     * Calculate the y-coordinate for isometric rendering
     * @param col The column location
     * @param row The row location
     * @return y-coordinate
     */
    public static int getIsometric1Y(final double col, final double row)
    {
        return (int)((col + row) * (HEIGHT / 2));
    }
    
    /**
     * Calculate the x-coordinate for isometric rendering
     * @param piece The piece containing the column, row location
     * @return x-coordinate
     */
    public static int getIsometric1X(final Piece piece)
    {
        return getIsometric1X(piece.getCol(), piece.getRow());
    }
    
    /**
     * Calculate the x-coordinate for isometric rendering
     * @param block The block containing the column, row location
     * @return x-coordinate
     */
    public static int getIsometric1X(final Block block)
    {
        return getIsometric1X(block.getCol(), block.getRow());
    }
    
    /**
     * Calculate the x-coordinate for isometric rendering
     * @param col The column location
     * @param row The row location
     * @return x-coordinate
     */
    public static int getIsometric1X(final double col, final double row)
    {
        return (int)((col - row) * (WIDTH / 2));
    }
    
    /**
     * Calculate the y-coordinate for isometric rendering
     * @param piece The piece containing the column, row location
     * @return y-coordinate
     */
    public static int getIsometric2Y(final Piece piece)
    {
        return getIsometric2Y(piece.getCol(), piece.getRow());
    }
    
    /**
     * Calculate the y-coordinate for isometric rendering
     * @param block The block containing the column, row location
     * @return y-coordinate
     */
    public static int getIsometric2Y(final Block block)
    {
        return getIsometric2Y(block.getCol(), block.getRow());
    }
    
    /**
     * Calculate the y-coordinate for isometric rendering
     * @param col The column location
     * @param row The row location
     * @return y-coordinate
     */
    public static int getIsometric2Y(final double col, final double row)
    {
        return (int)((col + row) * (ISOMETRIC_HEIGHT/2));
    }
    
    /**
     * Calculate the x-coordinate for isometric rendering
     * @param piece The piece containing the column location
     * @return x-coordinate
     */
    public static int getIsometric2X(final Piece piece)
    {
        return getIsometric2X(piece.getCol());
    }
    
    /**
     * Calculate the x-coordinate for isometric rendering
     * @param block The block containing the column location
     * @return x-coordinate
     */
    public static int getIsometric2X(final Block block)
    {
        return getIsometric2X(block.getCol());
    }
    
    /**
     * Calculate the x-coordinate for isometric rendering
     * @param col The column location
     * @return x-coordinate
     */
    public static int getIsometric2X(final double col)
    {
        return (int)(col * (ISOMETRIC_WIDTH / 2));
    }
    
    /**
     * Draw an isometric cube.
     * @param graphics Object used to draw shape
     * @param x starting x-coordinate
     * @param y starting y-coordinate
     */
    public void renderIsometric1(final Graphics graphics, final double x, final double y)
    {
        //assign polygon coordinates
        assignCoordinates((int)x, (int)y, CustomMenu.RENDER_ISOMETRIC_1);
        
        //render isometric block
        render(graphics, CustomMenu.RENDER_ISOMETRIC_1);
    }
    
    /**
     * Draw an isometric cube.
     * @param graphics Object used to draw shape
     * @param x starting x-coordinate
     * @param y starting y-coordinate
     */
    public void renderIsometric2(final Graphics graphics, final double x, final double y)
    {
        //assign polygon coordinates
        assignCoordinates((int)x, (int)y, CustomMenu.RENDER_ISOMETRIC_2);
        
        //render isometric block
        render(graphics, CustomMenu.RENDER_ISOMETRIC_2);
    }
    
    /**
     * Render polygon(s) here
     * @param graphics Object used to draw block
     * @param renderIndex The way we want to render our block
     */
    private void render(final Graphics graphics, final int renderIndex)
    {
        switch (renderIndex)
        {
            case CustomMenu.RENDER_ISOMETRIC_1:
            case CustomMenu.RENDER_ISOMETRIC_2:
                //set block color and fill shape
                graphics.setColor(getColor());

                if (topSide != null)
                    graphics.fillPolygon(topSide);

                if (rightSide != null)
                    graphics.fillPolygon(rightSide);

                if (frontSide != null)
                    graphics.fillPolygon(frontSide);

                //set outline color and draw outline
                graphics.setColor(Color.WHITE);

                if (frontSide != null)
                    graphics.drawPolygon(frontSide);

                if (rightSide != null)
                    graphics.drawPolygon(rightSide);

                if (topSide != null)
                    graphics.drawPolygon(topSide);
                break;
                
            case CustomMenu.RENDER_2D:
                
                //set block color and fill shape
                graphics.setColor(getColor());
                
                if (frontSide != null)
                    graphics.fillPolygon(frontSide);
                
                //set outline color and draw outline
                graphics.setColor(Color.WHITE);
                
                if (frontSide != null)
                    graphics.drawPolygon(frontSide);
                break;
        }
    }
    
    /**
     * Draw the block
     * @param graphics Object used to create image
     * @param outline Do we draw the outline
     * @param x Starting x-coordinate of location
     * @param y Starting y-coordinate of location
     */
    public void render2d(final Graphics graphics, final int x, final int y)
    {
        //assign polygon coordinates
        assignCoordinates((int)x, (int)y, CustomMenu.RENDER_2D);
        
        //render isometric block
        render(graphics, CustomMenu.RENDER_2D);
    }
}